package it.unibo.es1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {
	final List<Button> buttons;
	final int max;

	public LogicsImpl(int size) {
		this.buttons = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			buttons.add(new Button());
		}
		this.max = size;
	}

	@Override
	public int size() {
		return this.buttons.size();
	}

	@Override
	public List<Integer> values() {
		return this.buttons.stream().map(Button::getValue).collect(Collectors.toList());
	}

	@Override
	public List<Boolean> enablings() {
		return this.buttons.stream().map(Button::isEnabled).collect(Collectors.toList());
	}

	@Override
	public int hit(int elem) {
		this.buttons.get(elem).increment();
		return this.buttons.get(elem).getValue();
	}

	@Override
	public String result() {
		return "<<"+this.buttons.stream().map(b -> String.valueOf(b.getValue())).collect(Collectors.joining("|"))+">>";
	}

	@Override
	public boolean toQuit() {
		Integer valueOfFirst;
		if (!this.buttons.isEmpty()){
			valueOfFirst = this.buttons.getFirst().getValue();
			return this.buttons.stream().map(Button::getValue).allMatch(valueOfFirst::equals);
		}
		return true;
	}

	private final class Button {
		int value;
		boolean enabled;

		Button() {
			this.value = 0;
			this.enabled = true;
		}

		int getValue() {
			return this.value;
		}

		boolean isEnabled() {
			if (this.value >= max) {
				this.enabled = false;
			}
			return this.enabled;
		}

		void increment() {
			this.value++;
		}
	}
}
