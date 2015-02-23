package util;

public class Linkedlist {

	private Node head;
	private int size;

	public Linkedlist() {
		head = new Node();
		size = 0;
	}

	public int size() {
		return size;
	}
	
	public Node getHead() {
		return head;
	}

	public void insert(Object obj) {
		if (obj == null)
			return;

		Node node = new Node(obj);
		node.next = head.next;
		head.next = node;
		size++;
	}

	public void remove(Object obj) {
		if (obj == null)
			return;

		ListIterator itr = new ListIterator(head);
		Node current;
		Node previous;

		while (itr.isValid()) {
			previous = itr.retrieveNode();
			itr.advance();
			current = itr.retrieveNode();
			if (current.data.equals(obj)) {
				previous.next = current.next;
				size--;
				break;
			}
		}

	}

	public boolean isEmpty() {
		return (head.next == null);
	}

	public void empty() {
		head.next = null;
	}

	public Object getFirst() {
		if (head.next == null)
			return null;

		return head.next.data;
	}

	public boolean find(Object obj) {
		if (obj == null || head == null)
			return false;
		ListIterator itr = new ListIterator(head);
		while (itr.isValid()) {
			itr.advance();
			if (itr.retrieveData().equals(obj))
				return true;
		}
		return false;

	}

	public Object findPrevious(Object obj) {
		if (obj == null)
			return null;
		ListIterator itr = new ListIterator(head);
		Node previous = null;
		while (itr.isValid()) {
			previous = itr.retrieveNode();
			itr.advance();
			if (itr.retrieveData().equals(obj))
				return previous.data;

		}

		return null;
	}

	public Linkedlist reverse() {

		Linkedlist newList = new Linkedlist();
		ListIterator itr = new ListIterator(head);
		while (itr.isValid()) {
			itr.advance();
			newList.insert(itr.retrieveData());
		}
		
		return newList;
	} 
	
	public static Linkedlist integerSum(Linkedlist one, Linkedlist two) {
		
		Linkedlist newlist = new Linkedlist();
		Node listOne = one.getHead();
		Node listTwo = two.getHead();
		int carry = 0;
		while(listOne.next != null || listTwo.next !=null)
		{
			if(listOne.next != null)
			{
				carry += (Integer) listOne.next.data;
				listOne = listOne.next;
			}
			
			if(listTwo.next != null)
			{
				carry += (Integer) listTwo.next.data;
				listTwo = listTwo.next;
			}
			
			newlist.insert(carry%10);
			carry = carry/10;
			
			System.out.println("new List:" + newlist);
		}
		
		if(carry == 1)
			newlist.insert(carry);
		
		return newlist;
			
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();

		ListIterator itr = new ListIterator(head);
		sb.append("HEAD");
		while (itr.isValid()) {
			itr.advance();
			sb.append("->").append(itr.retrieveData());
		}

		return sb.toString();
	}

	public static void main(String[] args) {

		Linkedlist list = new Linkedlist();
		list.insert("a");
		list.insert("b");
		list.insert(3);

		System.out.println(list.toString());
		System.out.println(list.size());

		list.remove("a");
		System.out.println(list);
		System.out.println(list.size());
		
		System.out.println(list.reverse());
		
		Linkedlist one = new Linkedlist();
		Linkedlist two = new Linkedlist();
		
		one.insert(3);
		one.insert(9);
		one.insert(8);
		
		two.insert(3);
		two.insert(9);
		two.insert(8);
		
		System.out.println(integerSum(one, two));
		
	}
}

class ListIterator {

	Node current = null;

	public ListIterator(Node node) {
		current = node;
	}

	public boolean isValid() {
		return (current != null);
	}

	public void advance() {
		if (isValid())
			current = current.next;

	}

	public Object retrieveData() {
		if (isValid())
			return current.data;
		else
			return null;
	}

	public Node retrieveNode() {
		if (isValid())
			return current;
		else
			return null;
	}

}

class Node {
	Object data;
	Node next;

	public Node() {
		this(null, null);
	}

	public Node(Object data) {
		this(data, null);

	}

	public Node(Object data, Node next) {
		this.data = data;
		this.next = next;
	}
}
