public class BasicCalculatorIII {
    public boolean hasPrecedence(char pre_op, char cur_op) {
		if(pre_op == '(' || pre_op == ')') return false;
		else if((pre_op == '+' || pre_op == '-') && (cur_op == '*' || cur_op == '/')) return false;
		return true;
	}

	public long cal(long x, long y, char op) {
		if(op == '+') {
			return x + y;
		} else if(op == '-') {
			return x - y;
		} else if(op == '*') {
			return x * y;
		} else {
			return x / y;
		}
	}

	public long eval(String exp) {
		if(exp == null || exp.length() == 0) return 0;

		char[] ce = exp.toCharArray();
		int n = ce.length;
		LinkedList<Long> nstack = new LinkedList<Long> ();
		LinkedList<Character> opstack = new LinkedList<Character> ();

		int i = 0;
		while(i < n) {
			if(ce[i] == ' ') {
				++i;

			} else if(ce[i] == '(') {
				opstack.addFirst(ce[i]);
				++i;

			} else if(ce[i] == ')') {
				while(!opstack.isEmpty() && opstack.peekFirst() != '(') {
					long operand2 = nstack.pollFirst();
					long operand1 = nstack.pollFirst();
					nstack.addFirst(cal(operand1, operand2, opstack.pollFirst()));
				}
				opstack.pollFirst();
				++i;

			} else if(ce[i] >= '0' && ce[i] <= '9') {
				StringBuffer sb = new StringBuffer();
				while(i < n && (ce[i] >= '0' && ce[i] <= '9')) {
					sb.append(ce[i]);
					++i;
				}
				nstack.addFirst(Long.parseLong(sb.toString()));

			} else {
				char cur_op = ce[i];
				while(!opstack.isEmpty() && hasPrecedence(opstack.peekFirst(), cur_op)) {
					long operand2 = nstack.pollFirst();
					long operand1 = nstack.pollFirst();
					nstack.addFirst(cal(operand1, operand2, opstack.pollFirst()));
				}
				opstack.addFirst(cur_op);
				++i;
			}
		}

		while(!opstack.isEmpty()) {
			long operand2 = nstack.pollFirst();
			long operand1 = nstack.pollFirst();
			nstack.addFirst(cal(operand1, operand2, opstack.pollFirst()));
		}

		return nstack.pollFirst();
	}     
}
