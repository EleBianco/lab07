package it.unibo.inner;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;


public class ImplIterableWithPolicy<T> implements IterableWithPolicy<T>{

    private List<T> elements;
    private Predicate<T> filter;

    public ImplIterableWithPolicy(final T[] elements){
        this(elements, new Predicate<T>() {
            public boolean test(T elem){
                return true;
            }
        });
    }

    public ImplIterableWithPolicy(final T[] elements, final Predicate<T> filter){
        this.elements=List.of(Objects.requireNonNull(elements));
        this.filter = filter;
    }

    public Iterator<T> iterator() {
        return new IteratoreBianco();
    }

    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    public class IteratoreBianco implements Iterator<T>{

        private int i = 0;

        public boolean hasNext() {
            while(i < elements.size()){
                if(filter.test(elements.get(i))){
                    return true;
                }
                i++;
            }
            return false;
        }

        public T next() {
            return elements.get(i++);
        }

    }

}
