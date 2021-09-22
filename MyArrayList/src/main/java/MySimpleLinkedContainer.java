public class MySimpleLinkedContainer<E> implements MySimpleLinked<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size = 0;

    public MySimpleLinkedContainer() {
        lastNode = new Node<>(null, null, firstNode);
        firstNode = new Node<>(null, lastNode, null);
    }

    @Override
    public void addLast(E e) {
        Node<E> previous = lastNode;
        previous.setCurrentElement(e);
        lastNode = new Node<>(null, previous, null);
        previous.setNextElement(lastNode);
        size++;
    }

    @Override
    public void addFirst(E e) {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getElementByIndex(int counter) {
        Node<E> target = firstNode.getNextElement();
        for (int i = 0; i < counter; i++) {
            target = getNextElement(target);
        }
        return target.getCurrentElement();
    }

    private Node<E> getNextElement(Node<E> currentElement) {
        return currentElement.getNextElement();
    }

    private class Node<E> {
        private E currentElement;
        private Node<E> nextElement;
        private Node<E> previousElement;

        public Node(E currentElement, Node<E> nextElement, Node<E> previousElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
            this.previousElement = previousElement;
        }

        public E getCurrentElement() {
            return currentElement;
        }

        public void setCurrentElement(E currentElement) {
            this.currentElement = currentElement;
        }

        public Node<E> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<E> nextElement) {
            this.nextElement = nextElement;
        }

        public Node<E> getPreviousElement() {
            return previousElement;
        }

        public void setPreviousElement(Node<E> previousElement) {
            this.previousElement = previousElement;
        }
    }
}
