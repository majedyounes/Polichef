package polichef;

import java.util.*;

public class ComparatoreDiPiattiPerNumeroIngredienti implements Comparator<Piatto>{

	public int compare(Piatto o1, Piatto o2) {
		return o1.numeroIngredienti-o2.numeroIngredienti;
	}

}
