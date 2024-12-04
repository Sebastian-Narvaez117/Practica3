package com.example.controller.dao;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.controller.tda.models.Familia;
import com.example.controller.tda.models.enumerator.Combustible;

public class FamiliaDao extends AdapterDao<Familia> {
    private Familia familia;
    private LinkedList<Familia> listAll;

    public FamiliaDao() {
        super(Familia.class);

    }

    public Familia getFamilia() {
        if (familia == null) {
            familia = new Familia();
        }
        return this.familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public LinkedList<Familia> getlistAll() {
        if (listAll == null) {
            listAll = new LinkedList<>();
        }
        return listAll();
    }

    public Boolean update() throws Exception {
        this.merge(getFamilia(), getFamilia().getId() - 1);
        this.listAll = listAll();
        return true;
    }

    public Boolean save() throws Exception {
        Integer id = listAll().getSize() + 1;
        familia.setId(id);
        this.persist(this.familia);
        this.listAll = listAll();
        return true;
    }

    public Combustible getCombustibles(String tipo) {
        return Combustible.valueOf(tipo);
    }

    public Combustible[] getCombustibles() {
        return Combustible.values();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public LinkedList order(Integer type_order, String atributo) {
        LinkedList listita = listAll();
        if (!listAll().isEmpty()) {
            Familia[] lista = (Familia[]) listita.toArray();
            listita.reset();
            for (int i = 1; i < lista.length; i++) {
                Familia aux = lista[i]; // valor a ordenar
                int j = i - 1; // indice anteiror"apellido"
                while (j >= 0 && (verify(lista[j], aux, type_order, atributo))) {
                    lista[j + 1] = lista[j--]; // desplaza elementos hacia la derecha
                }
                lista[j + 1] = aux; // inserta el valor en su posicion
            }
            listita.toList(lista);
        }

        return listita;
    }

    private Boolean verify(Familia a, Familia b, Integer type_order, String atributo) {
        if (type_order == 1) {
            if (atributo.equalsIgnoreCase("apellido")) {
                return a.getApellido().compareTo(b.getApellido()) > 0;
            } else if (atributo.equalsIgnoreCase("ID")) {
                return a.getId() > b.getId();
            }
        } else {
            if (atributo.equalsIgnoreCase("apellido")) {
                return a.getApellido().compareTo(b.getApellido()) < 0;
            } else if (atributo.equalsIgnoreCase("ID")) {
                return a.getId() < b.getId();
            }
        }
        return false;

    }

    public LinkedList<Familia> orderQuicksort(Integer typeOrder, String attribute) {
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            try {
                listita.Quicksort(attribute, typeOrder); // Usa el Quicksort implementado en LinkedList
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listita;
    }

    public LinkedList<Familia> orderMergesort(Integer typeOrder, String atributo) {
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            try {
                listita.mergeSort(atributo, typeOrder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listita;
    }

    public LinkedList<Familia> orderShellsort(Integer typeOrder, String atributo) {
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            try {
                listita.shellSort(atributo, typeOrder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listita;
    }

    public LinkedList<Familia> buscar_apellidos(String texto) {
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            Familia[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {

                if (aux[i].getApellido().toLowerCase().startsWith(texto.toLowerCase())) {

                    lista.add(aux[i]);
                }
            }
        }
        return lista;
    }

    public Familia buscar_telefono(String texto) {
        Familia Familia = null;
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            Familia[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {

                if (aux[i].getTelefono().equals(texto)) {

                    Familia = aux[i];
                    break;
                }
            }
        }
        return Familia;
    }

    @SuppressWarnings("unchecked")
    public LinkedList<Familia> buscarPorApellido_binaria(String texto) {
        LinkedList<Familia> listita = listAll();
        LinkedList<Familia> resultado = new LinkedList<>();
        if (!listita.isEmpty()) {
            // Ordenamos la lista por apellido en orden ascendente
            listita = order(1, "apellido");

            // Convertimos la lista en un arreglo para usar búsqueda binaria
            Familia[] array = listita.toArray();

            int start = 0;
            int end = array.length - 1;
            int mid = -1;

            // Búsqueda binaria para localizar el inicio del rango de coincidencias
            while (start <= end) {
                mid = start + (end - start) / 2;
                int comparison = array[mid].getApellido().compareToIgnoreCase(texto);

                if (array[mid].getApellido().toLowerCase().startsWith(texto.toLowerCase())) {
                    break; // Encontramos un punto de partida
                } else if (comparison < 0) {
                    start = mid + 1; // Buscar en la mitad derecha
                } else {
                    end = mid - 1; // Buscar en la mitad izquierda  
                }
            }

            // Si encontramos un punto de coincidencia, buscamos hacia los lados
            if (mid != -1 && array[mid].getApellido().toLowerCase().startsWith(texto.toLowerCase())) {
                // Buscar hacia la izquierda
                int left = mid;
                while (left >= 0 && array[left].getApellido().toLowerCase().startsWith(texto.toLowerCase())) {
                    resultado.add(array[left]);
                    left--;
                }

                // Buscar hacia la derecha
                int right = mid + 1;
                while (right < array.length
                        && array[right].getApellido().toLowerCase().startsWith(texto.toLowerCase())) {
                    resultado.add(array[right]);
                    right++;
                }
            }
        }
        return resultado; // Devuelve la lista con las coincidencias
    }


    

    @SuppressWarnings("unchecked")
    public Familia buscarPorTelefono_binaria(String telefono) {
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            // Ordenamos la lista por teléfono en orden ascendente
            listita = order(1, "telefono");

            // Convertimos la lista en un arreglo para usar búsqueda binaria
            Familia[] array = listita.toArray();

            int start = 0;
            int end = array.length - 1;

            // Implementación de búsqueda binaria iterativa
            while (start <= end) {
                int mid = start + (end - start) / 2;
                int comparison = array[mid].getTelefono().compareTo(telefono);

                if (comparison == 0) {
                    return array[mid]; // Devuelve el objeto si se encuentra
                } else if (comparison < 0) {
                    start = mid + 1; // Buscar en la mitad derecha
                } else {
                    end = mid - 1; // Buscar en la mitad izquierda
                }
            }
        }
        return null; // Devuelve null si no se encuentra
    }




    //IMPLEMENTACION DE BUSQUEDA LINEAL

    public LinkedList<Familia> buscarPorApellido_lineal(String atributo) {
        LinkedList<Familia> resultado = new LinkedList<>();
        LinkedList<Familia> listita = listAll();

        if (!listita.isEmpty()) {
            Familia[] array = listita.toArray();
            for (Familia familia : array) {
                if (familia.getApellido().toLowerCase().startsWith(atributo.toLowerCase())) {
                    resultado.add(familia);
                }
            }
        }
        return resultado; // Devuelve la lista con las coincidencias
    }

    public Familia buscarPorTelefono_lineal(String atributo) {
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            Familia[] array = listita.toArray();
            for (Familia familia : array) {
                if (familia.getTelefono().equals(atributo)) {
                    return familia; // Devuelve el objeto si se encuentra
                }
            }
        }
        return null; // Devuelve null si no se encuentra

    }
    

}
