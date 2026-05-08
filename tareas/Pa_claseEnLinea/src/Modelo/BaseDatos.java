package Modelo;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class BaseDatos {

    private final Connection conexion;

    public BaseDatos(Connection conexion) {
        this.conexion = conexion;
    }

    public ArrayList<String[]> consultar(String tabla, String campos,
                                         String condicion, Object[] parametros) {
        String select = (campos == null || campos.isEmpty()) ? "*" : campos;
        String sql = "SELECT " + select + " FROM " + tabla;

        if (condicion != null && !condicion.isEmpty()) {
            sql += " WHERE " + condicion;
        }

        ArrayList<String[]> resultados = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            if (parametros != null) {
                for (int i = 0; i < parametros.length; i++) {
                    ps.setObject(i + 1, parametros[i]);
                }
            }

            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int numColumnas = rsmd.getColumnCount();

                while (rs.next()) {
                    String[] fila = new String[numColumnas];
                    for (int i = 1; i <= numColumnas; i++) {
                        fila[i - 1] = rs.getString(i);
                    }
                    resultados.add(fila);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error en consultar(): " + e.getMessage());
            System.err.println("SQL: " + sql);
        }

        return resultados;
    }

    public ArrayList<String[]> consultar(String tabla, String campos) {
        return consultar(tabla, campos, null, null);
    }


    public <T> ArrayList<T> consultarAObjeto(String sql, Object[] parametros, Class<T> clase) {
        ArrayList<T> resultados = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            if (parametros != null) {
                for (int i = 0; i < parametros.length; i++) {
                    ps.setObject(i + 1, parametros[i]);
                }
            }

            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int numColumnas = rsmd.getColumnCount();

                while (rs.next()) {
                    
                    T objeto = clase.getDeclaredConstructor().newInstance();

                    for (int i = 1; i <= numColumnas; i++) {
                        String nombreColumna = rsmd.getColumnLabel(i); 
                        String nombreCampo   = snakeToCamel(nombreColumna);
                        Object valorColumna  = rs.getObject(i);

                        try {
                            Field campo = encontrarCampo(clase, nombreCampo);
                            campo.setAccessible(true); 
                            campo.set(objeto, convertirTipo(valorColumna, campo.getType()));
                        } catch (NoSuchFieldException e) {
               
                        }
                    }

                    resultados.add(objeto);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error en consultarAObjeto() — SQL: " + e.getMessage());
        } catch (ReflectiveOperationException e) {
            System.err.println("Error de reflexión en consultarAObjeto(): " + e.getMessage());
            System.err.println("Asegúrate de que la clase tenga un constructor público sin argumentos.");
        }

        return resultados;
    }

        private Field encontrarCampo(Class<?> clase, String nombreCampo) throws NoSuchFieldException {
        Class<?> actual = clase;
        while (actual != null && actual != Object.class) {
            for (Field f : actual.getDeclaredFields()) {
                if (f.getName().equalsIgnoreCase(nombreCampo)) {
                    return f;
                }
            }
            actual = actual.getSuperclass();
        }
        throw new NoSuchFieldException(nombreCampo);
    }

    private String snakeToCamel(String nombre) {
        if (!nombre.contains("_")) return nombre;
        StringBuilder sb = new StringBuilder();
        boolean siguienteMayuscula = false;
        for (char c : nombre.toCharArray()) {
            if (c == '_') {
                siguienteMayuscula = true;
            } else if (siguienteMayuscula) {
                sb.append(Character.toUpperCase(c));
                siguienteMayuscula = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private Object convertirTipo(Object valor, Class<?> tipo) {
        if (valor == null) return null;
        if (tipo.isAssignableFrom(valor.getClass())) return valor;
        String str = valor.toString();
        if (tipo == int.class     || tipo == Integer.class)   return Integer.parseInt(str);
        if (tipo == long.class    || tipo == Long.class)      return Long.parseLong(str);
        if (tipo == double.class  || tipo == Double.class)    return Double.parseDouble(str);
        if (tipo == boolean.class || tipo == Boolean.class)   return str.equals("1") || Boolean.parseBoolean(str);
        if (tipo == float.class   || tipo == Float.class)     return Float.parseFloat(str);
        return str; // Fallback a String
    }

   
     
    public void modificar(String tabla, Map<String, Object> valores,
                          String condicion, Object[] parametros) {

        if (valores == null || valores.isEmpty()) {
            System.err.println("modificar(): el mapa de valores está vacío.");
            return;
        }

        StringBuilder setClause = new StringBuilder();
        List<Object> listaValores = new ArrayList<>(valores.values());
        boolean primero = true;
        for (String col : valores.keySet()) {
            if (!primero) setClause.append(", ");
            setClause.append(col).append(" = ?");
            primero = false;
        }

       
        if (parametros != null) {
            Collections.addAll(listaValores, parametros);
        }

        String sql = "UPDATE " + tabla + " SET " + setClause;
        if (condicion != null && !condicion.isEmpty()) {
            sql += " WHERE " + condicion;
        }

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (int i = 0; i < listaValores.size(); i++) {
                ps.setObject(i + 1, listaValores.get(i));
            }
            int filas = ps.executeUpdate();
            System.out.println("Registros actualizados: " + filas);
        } catch (SQLException e) {
            System.err.println("Error en modificar(): " + e.getMessage());
            System.err.println("SQL: " + sql);
        }
    }


    public void insertar(String tabla, String campos, String[] valores) {
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < valores.length; i++) {
            placeholders.append("?");
            if (i < valores.length - 1) placeholders.append(",");
        }
        String sql = "INSERT INTO " + tabla + " (" + campos + ") VALUES (" + placeholders + ")";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (int i = 0; i < valores.length; i++) {
                ps.setString(i + 1, valores[i]);
            }
            ps.executeUpdate();
            System.out.println("Registro insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
    }

    public void eliminar(String tabla, String campo, String valor) {
        String sql = "DELETE FROM " + tabla + " WHERE " + campo + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, valor);
            int filas = ps.executeUpdate();
            System.out.println("Registros eliminados: " + filas);
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }

    public boolean existe(String tabla, String condicion, Object[] parametros) {
        String sql = "SELECT 1 FROM " + tabla + " WHERE " + condicion;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            if (parametros != null) {
                for (int i = 0; i < parametros.length; i++) {
                    ps.setObject(i + 1, parametros[i]);
                }
            }
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error en existe(): " + e.getMessage());
            return false;
        }
    }
}