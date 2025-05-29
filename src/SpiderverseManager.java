import java.util.LinkedList;

public class SpiderverseManager {
    private LinkedList<SpiderverseHero> heroes;

    public SpiderverseManager() {
        heroes = new LinkedList<>();
    }

    public boolean agregarHeroe(SpiderverseHero nuevoHeroe) {
        // Validar que el código no exista
        if(buscarHeroe(nuevoHeroe.getCodigo()) != null) {
            return false;
        }
        // Agregar al inicio de la lista
        heroes.addFirst(nuevoHeroe);
        return true;
    }

    public SpiderverseHero buscarHeroe(int codigo) {
        for (SpiderverseHero hero : heroes) {
            if (hero.getCodigo() == codigo) {
                return hero;
            }
        }
        return null;
    }

    public boolean actualizarHeroe(int codigo, String nombre, String poder, String universo, int nivel) {
        SpiderverseHero heroe = buscarHeroe(codigo);
        if(heroe != null) {
            heroe.setNombre(nombre);
            heroe.setPoderEspecial(poder);
            heroe.setUniverso(universo);
            heroe.setNivelExperiencia(nivel);
            return true;
        }
        return false;
    }

    public LinkedList<SpiderverseHero> filtrarYOrdenarHeroes(String poderExcluir) {
        LinkedList<SpiderverseHero> filtrados = new LinkedList<>();

        // Filtrar
        for(SpiderverseHero hero : heroes) {
            if(!hero.getPoderEspecial().equals(poderExcluir)) {
                filtrados.add(hero);
            }
        }

        // Ordenar por nivel de experiencia (burbuja)
        for(int i = 0; i < filtrados.size()-1; i++) {
            for(int j = 0; j < filtrados.size()-i-1; j++) {
                if(filtrados.get(j).getNivelExperiencia() > filtrados.get(j+1).getNivelExperiencia()) {
                    SpiderverseHero temp = filtrados.get(j);
                    filtrados.set(j, filtrados.get(j+1));
                    filtrados.set(j+1, temp);
                }
            }
        }

        return filtrados;
    }

    public LinkedList<SpiderverseHero> getHeroes() {
        return heroes;
    }
}