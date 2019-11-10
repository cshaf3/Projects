package cs445.lab5;

public class MinStack<T extends Comparable<T>> extends LinkedStack<T> {
	// The private, second stack
	private StackInterface<T> minimums;
	
	public MinStack() {
		// TODO: instantiate the stack of minimums
		minimums = new LinkedStack<>();
	}
	
	@Override
	public void push(T newEntry)
	{
		// TODO: Use the stack of minimums to keep track of the lowest values on the stack.  Remember that peeking or popping from an empty stack results in an EmptyStackException
		if(minimums.isEmpty())
		{
			System.out.println("EMPTY STACK");
		}
		// TODO: Use 'super' to push to the main stack
		else if(newEntry.compareTo(minimums.peek()) < 0 || newEntry.compareTo(minimums.peek()) == 0)
		{
			super.push(newEntry);
		}
	}
	
	@Override
	public T pop() {
		// TODO: Get the top-most entry from the main stack.
		T top = super.pop();
		
		// TODO: Compare the entry to the entry on top of the stack of minimums (if it isn't empty) to determine if you need to
		// pop from there as well
		if(top.compareTo(minimums.peek()) == 0)
		{
			minimums.pop();
		}
		
		// TODO: Return the popped entry
		return top;
	}
	
	// getMin returns the lowest value on the stack in O(1) time
	public T getMin() {
		// TODO: Complete this method
		T min = minimums.peek();
		return min;
	}
}