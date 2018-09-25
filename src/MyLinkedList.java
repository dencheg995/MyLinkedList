public class MyLinkedList<E> {
   private Node<E> first;
   private Node<E> last;
   private int size = 0;
    static class Node<E>{
        E element;
        Node<E> next;
        Node<E> last;

        public Node(E element, Node<E> next, Node<E> pred) {
            this.element = element;
            this.next = next;
            this.last = pred;
        }
    }

    private void linkFirst(E element){
        Node <E> f = first;
        Node<E> newNode = new Node<>(element, null, first);
        first = newNode;
        if(f == null){
           last=newNode;
        }
        else {
            f.next = newNode;
        }
        size++;
    }

    private void linkLast(E element){
        Node<E> l = last;
        Node<E> newNode = new Node<>(element, null, l);
        last = newNode;
        if(l == null){
            first = newNode;
        }
        else {
            l.next = newNode;
        }
        size++;
    }

    public boolean addFirst(E element){
        linkFirst(element);
        return true;
    }

    public boolean addLast(E element){
        linkLast(element);
        return true;
    }

    public boolean add(E element){
      linkLast(element);
      return true;
    }

    public int size(){return size;}

    public E getFirst(){
        Node<E> f = first;
        return f.element;
    }

    public E getLast(){
        Node<E> l = last;
        return l.element;
    }

    public int indexOf(E element){
        int index = 0;
        if(element == null) {
            for (Node<E> i = first; i != null; i = i.next) {
                if (i.element == null) {
                    return index;
                }
                index++;
            }

        }
        else {
            for(Node<E> i = first; i != null; i = i.next){
                if(element.equals(i.element)) {
                    return index;
                }
                index++;
            }

        }
        return -1;
    }

    public E get(int index){
        int ind = 0;
            for (Node<E> i = first; i != null; i = i.next) {
                if (ind == index) {
                    return i.element;
                }
                ind++;
            }
        return null;
    }

    public boolean contains(E element) {
        if (element == null) {
            for(Node<E> i = first; i!=null; i = i.next){
                if(i.element == null) return true;
                else return false;
            }
        }
        else {
            for(Node<E> i = first; i != null; i=i.next){
                if(element.equals(i.element)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsImprove(E element) {
        return indexOf(element) >=0;
    }

    public void clear() {
        for(Node<E> i = first; i != null; i = i.next){
           i.element = null;
        }
        size = 0;
    }

    public void set(int index, E element){
        int ind = 0;
        for(Node<E> i = first; i != null; i=i.next) {
            if (ind == index){
                i.element = element;
            }
            ind++;
        }
    }

    public void remove(int index) {
        int ind = 0;
        for (Node<E> i = first; i != null; i = i.next) {
            if (ind == index) {

               nextNode(i);
            }
            ind++;
        }
    }

    public void remove(E elements) {
       if(elements == null)
        for (Node<E> i = first; i != null; i = i.next) {
            if (i.element == null) {
                nextNode(i);
            }
        }
        else {
           for (Node<E> i = first; i != null; i = i.next) {
               if (elements.equals(i.element)) {
                   nextNode(i);
               }
           }
       }
    }

    E nextNode(Node<E> i) {
        E element = i.element;
        Node<E> pred = i.last;
        Node<E> next = i.next;

        if(pred == null){
            first = next;
        }
        else {
            pred.next = next;
            i.last = null;
        }

        if(next == null){
            last = pred;
        }
        else {
            next.last = pred;
            i.next = null;
        }

        i.element = null;
        size--;
        return element;
    }
}
