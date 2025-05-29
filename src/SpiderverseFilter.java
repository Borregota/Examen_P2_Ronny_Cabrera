import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SpiderverseFilter {
    public List<SpiderverseHero> filtrarPorPoder(List<SpiderverseHero> heroes, String poderExcluido) {
        List<SpiderverseHero> filtrados = new ArrayList<>();
        for (SpiderverseHero hero : heroes) {
            if (!hero.getPoderEspecial().equalsIgnoreCase(poderExcluido)) {
                filtrados.add(hero);
            }
        }

        filtrados.sort(Comparator.comparingInt(SpiderverseHero::getNivelExperiencia));
        return filtrados;
    }
}

