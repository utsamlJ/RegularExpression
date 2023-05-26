package com.study.container.collection.copy.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * @USER: jys
 * @DATE: 2023/5/26
 * @TIME: 10:10
 * @DAY_NAME_FULL: 星期五
 */
public abstract class MyAbstractCollection<E> implements Collection<E> {
    /**
     * 唯一的构造函数，用于子类构造函数的调用，隐式的构造函数
     */
    protected MyAbstractCollection() {
    }

    /**
     * 返回集合拥有的元素个数
     *
     * @return
     */
    @Override
    public abstract int size();

    /**
     * 判断集合是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 判断集合是否包含指定元素
     *
     * @param o 指定元素
     * @return 如果有指定对象，返回true，否则false
     * @throws ClassCastException   当指定的元素与集合元素的种类不同时抛出这个异常
     *                              (<a href="#optional-restrictions">optional 可选的</a>)
     * @throws NullPointerException 当指定元素为null，且该集合不允许null存入时，抛出这个异常
     *                              (<a href="#optional-restrictions">optional 可选的</a>)
     */
    @Override
    public boolean contains(Object o) {
        Iterator<E> iterator = iterator();
        if (o == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (o.equals(iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 返回一个迭代器，用于迭代当前集合
     *
     * @return 一个迭代器，用于迭代当前集合
     */
    @Override
    public abstract Iterator<E> iterator();

    /**
     * 允许的最大数组大小。
     * 有的虚拟机会在数组中加一些保留字段。
     * 如果想分配更大的数组，可能会导致
     * OutOfMemoryError: Requested array size exceeds VM limit
     * OutOfMemoryError: 请求的数组大小超过虚拟机限制
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 当迭代器中返回的元素多于预期时，重新分配toArray中使用的数组，并完成填充。
     *
     * @param arr      数组
     * @param iterator 在该集合上迭代的迭代器
     * @param <T>      泛型
     * @return 包含给定数组中的元素，以及迭代器返回的其他元素的数组，修剪成大小
     */
    private static <T> T[] finishToArray(T[] arr, Iterator<?> iterator) {
        int i = arr.length;
        while (iterator.hasNext()) {
            int cap = arr.length;
            if (i == cap) {
//                扩容1.5倍，防止cap为1导致扩容失败，在最后进行了 +1 操作
//                是不是有点疑问，为什么都执行到这一步了，cap可能为1吗？
//                看toArray方法就可以知道，这是有可能的，在最开始的时候集合为空，在即将返回的时候，突然有值了就会出现这种情况
                int newCap = cap + (cap >> 1) + 1;
                if (newCap - MAX_ARRAY_SIZE > 0) {
                    newCap = hugeCapacity(cap + 1);
                }
                arr = Arrays.copyOf(arr, newCap);
            }
            arr[i++] = (T) iterator.next();
        }
        return (i == arr.length) ? arr : Arrays.copyOf(arr, i);
    }

    /**
     * 返回数组容量大小
     *
     * @param minCapacity 需要的最小容量
     * @return 数组容量大小
     */
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError("Required array size too large--所需数组太大");
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    /**
     * 返回包含此集合中所有元素的数组。
     * 如果该集合允许迭代器在迭代的过程中对元素个数进行修改，通过iterator.hasNext()不断的去判断真实大小，并将结果进行“修剪”。
     * 变小了直接截取，变大了调用另外的方法进行调整大小。
     *
     * @return 返回包含此集合中所有元素的数组。
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size()];
        Iterator<E> iterator = iterator();
//        要是这里时，集合的元素突然没有了会发生什么？
        for (int i = 0; i < arr.length; i++) {
            if (!iterator.hasNext()) {
                return Arrays.copyOf(arr, i);
            }
            arr[i] = iterator.next();
        }
        return iterator.hasNext() ? finishToArray(arr, iterator) : arr;
    }

    /**
     * 返回一个和参数数组类型相同的，存入了所有集合元素的数组。
     * 如果参数数组的大小满足存放所有集合元素，就用参数数组存放集合元素，
     * 并且，在存入所有集合元素之后，参数数组还有空间，
     * 会紧接着添加一个null元素，作为分割集合元素和参数数组中原数据之间的标记
     *
     * @param a   参数数组，用于指定返回的数组类型，条件允许的情况下，会使用这个数组存放集合的元素
     * @param <T> 泛型，使用多种类型
     * @return 返回一个和参数数组类型相同的，存入了所有集合元素的数组。
     * @throws ArrayStoreException  如果传入的数组类型不是集合元素的超类数组时，抛出该异常
     * @throws NullPointerException 如果传入的数组为 null，抛出该异常
     */
    @Override
    public <T> T[] toArray(T[] a) {
        int size = size();
//        判断参数数组a的大小是否能存下集合所有的元素
//        如果可以，arr就指向a（因为数组本身就是对象）
//        如果不可以，新建一个大小和集合元素个数相等，且类型和参入数组一致的新数组，并将arr指向这个新数组
//        不管如何，arr指向一个满足"当前"集合大小的数组中（为什么是当前，因为有可能在迭代器迭代遍历集合元素的过程中对集合进行增删操作）
        T[] arr = a.length >= size ? a : (T[]) java.lang.reflect.Array
                .newInstance(a.getClass().getComponentType(), size);
        Iterator<E> iterator = iterator();
//        要是这里时，集合的元素突然没有了会发生什么？
        for (int i = 0; i < arr.length; i++) {
            if (!iterator.hasNext()) {
                if (a == arr) {
//                    当 a.length >= size 才相等
//                    但是还在for循环里面，就已经迭代遍历结束了，说明
//                    a.length > size
                    arr[i] = null;
                } else if (a.length < i) {
//                    已经迭代结束了，而且不满足 a == arr 说明在创建 arr 数组之后，集合中的元素变少了
//                    arr 集合大了，需要进行裁剪
                    return Arrays.copyOf(arr, i);
                } else {
//                    1.首先集合中的元素因为某些原因，导致在迭代遍历的过程中个数减少，使迭代遍历完了。
//                    2.由第一个if条件不成立可以得知，在创建arr数组中有不少是空元素。
//                    3.由第二个if条件不成立得知，现在集合元素的个数不会超过参数传过来的数组。
//                    4.到执行到这里，就面临着：集合的元素变少了，用参数传过来的数组是可以存的下的，且已经迭代遍历完成了。
//                    5.所以开始使用参数传过来的数组，借用System.arraycopy方法，进行拷贝

//                    System.arraycopy的参数解释：
//                      第一个参数，表示要拷贝的目标数组（这里由于集合已经迭代遍历完了，并且将元素放到了arr这个新建的数组中）
//                      第二个参数，表示从目标数组的哪个位置开始拷贝，这里用的是下标表示位置
//                      第三个参数，表示将拷贝的数据放到哪个拷贝数组中
//                      第四个参数，表示从拷贝数组的哪个位置开始存放拷贝数据，这里用的也是下标表示
//                      第五个参数，表示要拷贝多少个数据（这里肯定是arr数组中的所有元素了，因为i局部变量一直起到一个计数的作用，所以可以传i过去）
                    System.arraycopy(arr, 0, a, 0, i);
//                    当拷贝完成之后，如果参数数组存下所有元素之后还有空间，会紧接着加上一个null元素作为分割集合元素和参数数组中原数据之间的标记
                    if (a.length > i) {
                        a[i] = null;
                    }
                }
//                返回参数数组，特殊情况已经通过return提前返回了
                return a;
            }
//            说明集合还在迭代遍历中，正常存入元素
            arr[i] = (T) iterator.next();
        }
//        判断在迭代遍历过程中有没有增加元素，如果没有，就返回arr，如果有，调用finishToArray方法接着完成数组
        return iterator.hasNext() ? finishToArray(arr, iterator) : arr;
    }

    /**
     * 因为这个方法是可选项，有的集合实现类不支持add方法，并且不同的集合实现类存储元素的方式不同，无法写出通用的方法。
     * 所以这里就先同一抛出不支持该方法的异常，支持该方法的实现类需要根据自身的集合存储元素方式进行特异化操作。
     * 如果支持该方法的实现类因为调用该方法使集合发生改变就返回true，都在返回false
     *
     * @param e 要存入的元素
     * @return 默认值抛出不支持该方法异常，如果支持该方法的实现类因为调用该方法使集合发生改变就返回true，都在返回false
     * @throws UnsupportedOperationException 如果该集合不支持此方法
     * @throws ClassCastException            如果指定元素类型不是集合元素类型及派生类，就抛出此异常
     * @throws NullPointerException          如果集合不支持存入 null，且指定元素为 null，抛出此异常
     * @throws IllegalArgumentException      如果传入的参数不合法，就抛出此异常
     * @throws IllegalStateException         由于插入限制，此时无法插入元素时，抛出此异常（不太清楚）
     */
    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * 删除集合中第一个与指定元素相同元素。
     * 判断是否相同：e为集合中的元素
     * o == null ? e == null : o.equals(e)
     *
     * @param o 指定的元素
     * @return 如果调用后删除了一个元素，返回 true，否则返回 false
     * @throws ClassCastException            如果指定元素类型不是集合元素类型及派生类，就抛出此异常
     * @throws NullPointerException          如果集合不支持存入 null，且指定元素为 null，就抛出该集合。
     * @throws UnsupportedOperationException 如果集合不支持此方法
     */
    @Override
    public boolean remove(Object o) {
        Iterator<E> iterator = iterator();
        if (o == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    iterator.remove();
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (o.equals(iterator.next())) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 如果集合包含指定集合的所有元素就返回 true，否则返回 false。
     * 该抽象提供的默认方法是遍历指定集合元素，不断调用自身的contains方法，如果发现有一个返回值是 false，就返回 false，否则最终返回 true
     *
     * @param c 指定集合
     * @return 集合包含指定集合中的所有元素返回 true，否则返回 false
     * @throws ClassCastException   如果指定集合中有一个或多个元素与集合(this)元素的类型不兼容，就抛出此异常。
     * @throws NullPointerException 两种情况抛出此异常：1.如果指定集合有一个或多个 null，并且集合(this)不支持存入 null 时。2.指定集合为 null 时。
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将指定集合中的元素都通过add方法尝试添加到(this)集合中
     * 但是返回值如果是true并不代表所有的元素都添加进去了，是只要有一个添加成功就返回true
     * 如果一个都没成功，那么就返回false
     *
     * @param c 指定集合
     * @return 当集合因为调用该方法而改变时返回 true，否则返回 false
     * @throws UnsupportedOperationException 如果集合不支持此方法
     * @throws ClassCastException            如果指定集合中的元素类型禁止添加到集合(this)中
     * @throws NullPointerException          如果指定集合是 null。或者指定集合含有 null，并且此集合不允许 null存在时。
     * @throws IllegalArgumentException      指定集合中元素的某个属性阻止了它被添加到这个集合中
     * @throws IllegalStateException         由于插入限制，当前不能添加所有元素
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
//        先设定一个标记，默认是false，表明(this)集合没有被修改
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
//                如果add方法的返回值为true，表明集合被修改了。
//                就将标记设为true。
                modified = true;
            }
        }
//        返回设定的标记
        return modified;
    }

    /**
     * 删除集合(this)中集合(this)元素与指定集合元素的交集。
     * 该抽象类提供的默认方法为：
     * 遍历(this)集合元素，只要该元素在指定集合中能找到，就删除。
     * 例如：(this)(假设是运行重复元素的集合实现类)集合有两个元素'1'，指定集合有'1'元素，那么(this)集合的两个元素'1'都会被删除。
     * 使用的迭代器进行遍历，并且用迭代器进行元素的移除操作。
     *
     * @param c 指定集合
     * @return 如果调用改变了集合，返回true
     * @throws UnsupportedOperationException 集合不支持此方法
     * @throws ClassCastException            此集合中的一个或多个元素的类型与指定的集合不兼容（类型不一致）(可选)
     * @throws NullPointerException          1.如果此集合包含一个或多个null元素，并且指定的集合不支持null元素(可选)，或者 2.指定的集合为null
     */
    @Override
    public boolean removeAll(Collection<?> c) {
//        就是判断指定集合 c 是否是 null
//        如果是 null，就抛出异常：NullPointerException
//        如果不是 null，就返回参数（在这里没必要去接收返回值）
        Objects.requireNonNull(c);
//        和addAll里的一样，做个标记，初始化是false，表示(this)集合有没有被改变。
        boolean modified = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
//            只要该元素能在指定集合中能找到，就移除，并且将标记设为 true，表示集合被修改了。
            if (c.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
//        返回标记
        return modified;
    }

    /**
     * 与removeAll方法完全相反，保留在指定集合中出现的元素
     *
     * @param c 指定集合
     * @return 如果调用改变了集合，返回True
     * @throws UnsupportedOperationException 如果此集合不支持retainAll操作
     * @throws ClassCastException            如果此集合中的一个或多个元素的类型与指定的集合不兼容(可选)
     * @throws NullPointerException          1.如果此集合包含一个或多个null元素，并且指定的集合不允许null元素(可选)，或者 2.指定的集合为null
     */
    @Override
    public boolean retainAll(Collection<?> c) {
//        就是判断指定集合 c 是否是 null
//        如果是 null，就抛出异常：NullPointerException
//        如果不是 null，就返回参数（在这里没必要去接收返回值）
        Objects.requireNonNull(c);
//        和上面一样，做个标记，初始化是false，表示(this)集合有没有被改变。
        boolean modified = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
//            和removeAll方法完全相反。
//            removeAll方法是在指定集合能找到的元素都删除
//            这里是：在指定集合找不到的元素都删除
            if (!c.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * 通过迭代器的方法，不断遍历，不断删除，直至清空为止。
     *
     * @throws UnsupportedOperationException 如果此集合不支持clear操作
     */
    @Override
    public void clear() {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }
}
