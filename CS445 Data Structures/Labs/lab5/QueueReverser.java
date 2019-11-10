package cs445.lab5;

public class QueueReverser {
	public static <T> void reverseQueue(QueueInterface<T> queue) {
		// TODO: Make the stack to use for reversing the queue
		StackInterface<T> stack = new LinkedStack<>();
		// TODO: Move all the elements from the queue to the stack
		while(!queue.isEmpty())
		{
			T t = queue.dequeue();
			stack.push(t);
		}
		// TODO: Move all of the elements back into the queue, reversing the original order
		while(!stack.isEmpty())
		{
			T t = stack.pop();
			queue.enqueue(t);
		}
	}
}