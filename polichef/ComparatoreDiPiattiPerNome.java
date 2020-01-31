package polichef;

import java.util.*;

public class ComparatoreDiPiattiPerNome implements Comparator<Piatto>{

	public int compare(Piatto o1, Piatto o2) {
		return o1.getNome().compareTo(o2.getNome());
	}

}
