package it.unibo.es1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

	private final List<Integer> values;

	public LogicsImpl(int size) {
		if (size < 1) {
			throw new IllegalArgumentException("Invalid number of buttons");
		}
		this.values = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			this.values.add(0);
		}
	}

	@Override
	public int size() {
		return this.values.size();
	}

	@Override
	public List<Integer> values() {
		return new ArrayList<>(this.values);
	}

	@Override
	public List<Boolean> enablings() {
		return this.values.stream()
				.map(val -> val < this.values.size())
				.toList();
	}

	@Override
	public int hit(int elem) {
		if (elem >= this.values.size()) {
			throw new IllegalArgumentException("There is no button of index " + elem);
		}
		this.values.set(elem,
				this.values.get(elem) + 1);
		return this.values.get(elem);
	}

	@Override
	public String result() {
		return this.values.stream()
				.map(val -> String.valueOf(val))
				.collect(Collectors.joining("|", "<<", ">>"));
	}

	@Override
	public boolean toQuit() {
		return this.values.stream()
				.filter(val -> val == this.values.get(0))
				.count() == this.values.size();
	}
}
