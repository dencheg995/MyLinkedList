import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size = 0;

    static class Node<E> {
        E element;
        Node<E> next;
        Node<E> pred;
        public Node(E element, Node<E> next, Node<E> pred) {
            this.element = element;
            this.next = next;
            this.pred = pred;
        }
    }

    private void linkFirst(E element) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(element, null, first);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.next = newNode;
        }
        size++;
    }

    private void linkLast(E element) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(element, null, l);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public boolean addFirst(E element) {
        linkFirst(element);
        return true;
    }

    public boolean addLast(E element) {
        linkLast(element);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null)
            for (Node<E> i = first; i != null; i = i.next) {
                if (i.element == null) {
                    nextNode(i);
                }
            }
        else {
            for (Node<E> i = first; i != null; i = i.next) {
                if (o.equals(i.element)) {
                    nextNode(i);
                }
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int ind = 0;
        int indt = 0;
        for(Node<E> i = first; i!=null; i = i.next) {
            if(c.contains(i.element)) {
                ind++;
            }
            indt++;
        }
        if(ind == indt) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if (a.length == 0) return false;
        Node<E> pred = null;
        Node<E> now;
        for(Node<E> i = first; i!=null; i = i.next) {
            Node<E> tempNode = new Node<>(i.element, i.next, i.pred);
            now = tempNode;
            pred = now.pred;
        }
        forAddAll(a, pred);
        size--;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int ind = 0;
        Object[] a = c.toArray();
        if(a.length == 0) return false;
        Node<E> pred;
        Node<E> now;
        for(Node<E> i = first; i!=null; i = i.next) {
            if(index == size) {
                pred = last;
            }
            else if(index == ind){
                Node<E> tempNode = new Node<>(i.element, i.next, i.pred);
                now = tempNode;
                pred = now.pred;
                forAddAll(a, pred);
                if (now == null) {
                    last = pred;
                } else {
                    pred.next = now;
                    now.pred = pred;
                }
            }

            ind++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Node<E> i = first; i!=null; i = i.next) {
            if(c.contains(i.element)) {
                Node<E> pred = i.pred;
                Node<E> next = i.next;
                if(pred == null) {
                    first = next;
                }
                else if(next ==null) last = pred;
                else {
                    pred.next = next;
                    next.pred = pred;
                }
                size--;
            }
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for(Node<E> i = first; i != null; i = i.next) {
            if(!c.contains(i.element)) {
                E element = i.element;
                Node<E> pred = i.pred;
                Node<E> next = i.next;
                if(pred == null) {
                    first = next;
                }
                else if(next == null) {
                    last = pred;
                }
                else {
                    element = null;
                    next.pred = pred;
                    pred.next = next;
                }
                size--;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0) return false;
        else return true;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] a = new Object[size];
        int ind = 0;
        for(Node<E> i = first; i!=null; i = i.next) {
           a[ind] = i.element;
           ind++;
        }
        return a;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E element) {
        linkLast(element);
        return true;
    }

    public E getFirst() {
        Node<E> f = first;
        return f.element;
    }

    public E getLast() {
        Node<E> l = last;
        return l.element;
    }

    public E get(int index) {
        int ind = 0;
        for (Node<E> i = first; i != null; i = i.next) {
            if (ind == index) {
                return i.element;
            }
            ind++;
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        int ind = 0;
        for (Node<E> i = first; i != null; i = i.next) {
            if (ind == index) {
                i.element = element;
            }
            ind++;
        }
        return null;
    }

    public void clear() {
        for (Node<E> i = first; i != null; i = i.next) {
            i.element = null;
        }
        size = 0;
    }


    @Override
    public void add(int index, E element) {
        int ind = 0;
        if(index == size) {
            linkLast(element);
        }
        else {
            for(Node<E> i = first; i!=null; i = i.next) {
                if(index == ind) {
                    Node<E> tempNode = new Node<>(i.element, i.next, i.pred);
                    listBefor(tempNode, element);
                }
                ind++;
            }
        }
    }

    @Override
    public E remove(int index) {
        int ind = 0;
        for (Node<E> i = first; i != null; i = i.next) {
            if (ind == index) {
               return nextNode(i);
            }
            ind++;
        }
        return null;
    }
    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> i = first; i != null; i = i.next) {
                if (i.element == null) {
                    return index;
                }
                index++;
            }

        } else {
            for (Node<E> i = first; i != null; i = i.next) {
                if (o.equals(i.element)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int ind = 0;
        for(Node<E> i = first; i!=null; i = i.next) {
            if(o.equals(i.element)) {
                ind++;
            }
        }
        return ind;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }



//    E nextNode(Node<E> i) {
//        E element = i.element;
//        Node<E> pred = i.last;
//        Node<E> next = i.next;
//
//        if(pred == null){//если предыдущий объект нашего элемента null
//            first = next;// то первому объекту присваиваем значение следущего элемента после нашего поданного
//        }
//        else { //иначе просто предущему элементу нашего объекта присваиваем следущее значение после нашего объекта
//            pred.next = next;
//            i.last = null; // а предыдущий узел обнляем
//        }
//
//        if(next == null){ // если следущий элемент после нашего объекта нулл
//            last = pred;// то последнему объекту присваиваем предыдущий от нашего объекта
//        }
//        else { // иначае следущему элементу после нашего объекта присваиваем предыдущее значение нашего объекта
//            next.last = pred;
//            i.next = null; // а след элемент после нашего объекта обнуляем
//        }
//
//        i.element = null; // наш элемент обнуляем
//        size--;// уменьшаем размер листа
//        return element;
//    }
//}

    E nextNode(Node<E> i) {
        E element = i.element;
        Node<E> pred = i.pred;
        Node<E> next = i.next;
        if (pred == null) {
            first = next;
        } else {
            pred.next = next;
            i.next = null;
        }
        if (next == null) {
            last = pred;
        } else {
            next.pred = pred;
            i.pred = null;
        }
        i.element = null;
        size--;
        return element;
    }

    private void listBefor(Node<E> temp, E element) {
        Node<E> pred = temp.pred;
        Node<E> newNode = new Node<>(element, temp, pred);
        temp.pred = newNode;
        if(pred == null) {
            first = newNode;
        }
        else {
            pred.next = newNode;
        }
        size++;
    }

    private void forAddAll(Object[] a, Node<E> pred) {
        for(Object o : a) {
            E e = (E) o;
            Node<E> newNode = new Node<>(e, null, pred);
            if(pred == null) {
                first = newNode;
            }
            else {
                pred.next = newNode;
                pred = newNode;
            }
            size++;
        }
    }
}
