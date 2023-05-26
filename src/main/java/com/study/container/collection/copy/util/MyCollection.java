package com.study.container.collection.copy.util;

/**
 * @USER: jys
 * @DATE: 2023/5/24
 * @TIME: 18:00
 * @DAY_NAME_FULL: 星期三
 */

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 抄 java.util.Collection 接口
 * 至于继承Collection接口也是无奈之举，因为在最后几个方法中，需要传递Collection的类型过去
 *
 * @param <E>
 */
public interface MyCollection<E> extends Collection<E>, Iterable<E> {

    /**
     * 返回集合中含有的元素个数
     * 如果集合包含的元素多于 Integer.MAX_VALUE，那就返回 Integer.MAX_VALUE。
     * Integer.MAX_VALUE = 2^31-1
     *
     * @return 集合中元素的个数
     */
    int size();

    /**
     * 判断集合是否是空集合(即不包含任何元素),如果是空集合就返回 true，否则就返回 false
     *
     * @return 如果集合中没有元素就返回 true，否则就返回 false
     */
    boolean isEmpty();

    /**
     * 如果集合包含指定元素就返回 true，都在返回 false。
     * 集合中至少含有一个元素 e，
     * 且 o == null ? e == null : o.equals(e) 的返回值为 true 时返回 true。
     *
     * @param o 要判断集合是否包含的元素
     * @return 当集合包含指定元素时返回 true，否则返回 false
     * @throws ClassCastException   当指定的元素与集合元素的种类不同时抛出这个异常
     *                              (<a href="#optional-restrictions">optional 可选的</a>)
     * @throws NullPointerException 当指定元素为null，且该集合不允许null存入时，抛出这个异常
     *                              (<a href="#optional-restrictions">optional 可选的</a>)
     */
    boolean contains(Object o);

    /**
     * 返回该集合元素的迭代器。
     * 对于元素的顺序没哟保证，除非实现类提供保证顺序。
     *
     * @return 返回一个该集合元素的迭代器
     */
    @Override
    Iterator<E> iterator();

    /**
     * 返回一个包含该集合所有元素的数组。
     * 元素在数组的顺序和迭代器顺序一致。
     * 哪怕该集合实际是数组维护的，返回的数组那也是新数组。
     *
     * @return 返回一个包含该集合所有元素的数组
     */
    Object[] toArray();

    /**
     * <p> 返回一个包含该集合所有元素的数组。
     * <p> 返回的数组类型和传入的数组类型一致。
     * <p>
     * <p> 如果数组的大小>=集合元素个数，会在传入的数组基础上操作。
     * <p> 在传入的数组中从下标0开始存入集合元素。
     * <p> 如果存入所有的集合元素后，数组还有空间，数组中存入最后一个集合元素的位置的下一位置置为null
     * <p>
     * <p> 如果 数组大小 < 集合元素个数，会新建一个与传入数组类型相同，且大小为集合元素个数的新数组。
     * <p> 在这个新数组上开始搬运集合元素。
     * <p>
     * <p> 例如：
     * <p> n为数组大小，m为集合元素个数。
     * <p> n >= m 时，传入数组的下标 0 ~ (m-1) 的位置搬运集合中的元素。
     * <p>         如果 n > m，会将 a[m] 置为 null
     * <p>         将传入数组返回。
     * <p> n < m 时，会根据传入数组的类型 T，创建一个大小为 m 新数组，开始搬运。
     * <p>         返回新数组。
     *
     * @param arr 传入的数组，数组的类型要包含集合元素的类型
     * @param <T> 泛型
     * @return 返回一个包含所有集合元素，且类型和传入的数组一致的数组。
     * @throws ArrayStoreException  如果传入的数组类型不是集合元素的超类数组时，抛出该异常
     * @throws NullPointerException 如果传入的数组为 null，抛出该异常
     */
    <T> T[] toArray(T[] arr);

    /**
     * 确保集合包含指定元素。
     * List 集合是添加指定元素进去
     * Set 因为不允许有重复元素出现，只能是确保包含指定元素。
     * <p>
     * 如果集合因为调用该方法而改变，就返回 true。
     * 否则就返回 false。
     * 例如：Set集合已经包含了指定元素了，在调用该方法时会返回false。
     * <p>
     * 不同的集合实现类对此方法的实现不一样，有的支持该方法，有的不支持。
     * 例如：Array.asList方法返回的List集合就不支持该方法，在调用该方法时会抛出 UnsupportedOperationException 异常
     *
     * @param e 指定元素
     * @return 当集合因为调用该方法而改变时返回 true，否则返回 false
     * @throws UnsupportedOperationException 如果该集合不支持此方法
     * @throws ClassCastException            如果指定元素类型不是集合元素类型及派生类，就抛出此异常
     * @throws NullPointerException          如果集合不支持存入 null，且指定元素为 null，抛出此异常
     * @throws IllegalArgumentException      如果传入的参数不合法，就抛出此异常
     * @throws IllegalStateException         由于插入限制，此时无法插入元素时，抛出此异常（不太清楚）
     */
    boolean add(E e);

    /**
     * 原意：
     * 从该集合中删除指定元素的单个实例（如果存在）（可选操作）。
     * 更正式地，删除元素e ，使得(o==null ? e==null : o.equals(e)) ，如果该集合包含一个或多个这样的元素。
     * 如果此集合包含指定的元素（或等效地，如果此集合由于调用而更改），则返回true 。
     *
     * @param o 指定的元素
     * @return 如果调用后删除了一个元素，返回True
     * @throws ClassCastException            如果指定元素类型不是集合元素类型及派生类，就抛出此异常
     * @throws NullPointerException          如果集合不支持存入 null，且指定元素为 null，就抛出该集合。
     * @throws UnsupportedOperationException 如果集合不支持此方法
     */
    boolean remove(Object o);

    /**
     * 如果集合包含指定集合的所有元素就返回 true，否则返回 false
     *
     * @param c 指定集合
     * @return 如果集合包含指定集合的所有元素就返回 true，否则返回 false。
     * @throws ClassCastException   如果指定集合中有一个或多个元素与集合(this)元素的类型不兼容，就抛出此异常。
     * @throws NullPointerException 两种情况抛出此异常：1.如果指定集合有一个或多个 null，并且集合(this)不支持存入 null 时。2.指定集合为 null 时。
     */
    boolean containsAll(MyCollection<?> c);

    /**
     * 将指定集合中的所有元素加入到集合(this)中。
     * <p>
     * 原意中后面两句中说啥未定义的，没看懂：
     * 如果在操作过程中修改了指定的集合，则此操作的行为是未定义的。
     * (这意味着如果指定的集合是这个集合，并且这个集合是非空的，那么这个调用的行为是未定义的。)
     *
     * @param c 指定集合
     * @return 当集合因为调用该方法而改变时返回 true，否则返回 false
     * @throws UnsupportedOperationException 如果集合不支持此方法
     * @throws ClassCastException            如果指定集合中的元素类型禁止添加到集合(this)中
     * @throws NullPointerException          如果指定集合是 null。或者指定集合含有 null，并且此集合不允许 null存在时。
     * @throws IllegalArgumentException      指定集合中元素的某个属性阻止了它被添加到这个集合中
     * @throws IllegalStateException         由于插入限制，当前不能添加所有元素
     */
    boolean addAll(MyCollection<? extends E> c);

    /**
     * 删除集合(this)中集合(this)元素与指定集合元素的交集
     *
     * @param c 指定集合
     * @return 如果调用改变了集合，返回True
     * @throws UnsupportedOperationException 集合不支持此方法
     * @throws ClassCastException            此集合中的一个或多个元素的类型与指定的集合不兼容（类型不一致）(可选)
     * @throws NullPointerException          1.如果此集合包含一个或多个null元素，并且指定的集合不支持null元素(可选)，或者 2.指定的集合为null
     */
    boolean removeAll(MyCollection<?> c);

    /**
     * 删除集合中满足给定谓词的所有元素。
     * 迭代期间或由谓词抛出的错误或运行时异常将传递给调用者。
     *
     * @param filter 对于要删除的元素，返回true的谓词
     * @return 如果删除了任何元素，返回True
     * @throws NullPointerException          如果指定的过滤器为null
     * @throws UnsupportedOperationException 如果无法从集合中删除元素。如果无法删除匹配的元素，或者通常不支持删除，则实现可能会抛出此异常。
     *                                       <p>
     *                                       <p>
     *                                       直接抄的，暂时不清楚啥意思
     */
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    /**
     * 只保留集合中包含在指定集合中的元素(可选操作)。
     * 换句话说，从该集合中删除不在指定集合中的所有元素。
     * 也就是只保留交集。
     *
     * @param c 指定集合
     * @return 如果调用改变了集合，返回True
     * @throws UnsupportedOperationException 如果此集合不支持retainAll操作
     * @throws ClassCastException            如果此集合中的一个或多个元素的类型与指定的集合不兼容(可选)
     * @throws NullPointerException          1.如果此集合包含一个或多个null元素，并且指定的集合不允许null元素(可选)，或者 2.指定的集合为null
     */
    boolean retainAll(MyCollection<?> c);

    /**
     * 将集合置空
     *
     * @throws UnsupportedOperationException 如果此集合不支持clear操作
     */
    void clear();

    /**
     * 这个抄的翻译有点问题，暂时埋坑
     * 就是判断指定对象与集合是否相等，想要借助重写Object类提供的equals方法实现
     * <p>
     * <p>
     * 比较指定对象与此集合是否相等。
     * <p>
     * 而Collection接口没有为对象的总合同添加任何规定。
     * “直接”实现Collection接口的程序员(换句话说，创建的类是Collection，但不是Set或List)在选择重写Object.equals时必须谨慎。
     * 其实不是必须这样做，最简单的做法是依赖于Object的实现，但实现者可能希望实现“值比较”来代替默认的“引用比较”。(List和Set接口强制进行这种值比较。)
     * <p>
     * 总承包为对象。Equals方法声明Equals必须是对称的(换句话说，当且仅当b. Equals (a)时，a. Equals (b))。
     * The contracts for List.equals and Set.equals表示列表只与其他列表相等，而sets表示与其他集合相等。
     * 因此，既不实现List也不实现Set接口的collection类的自定义equals方法在将集合与任何List或Set进行比较时必须返回false。
     * (按照同样的逻辑，一个类也不可能同时正确实现Set和List接口。)
     *
     * @param o 对象，以便与此集合进行相等性比较
     * @return 如果指定对象等于此集合，则为True
     */
    boolean equals(Object o);

    /**
     * 返回此集合的哈希码值。
     *
     * @return 返回此集合的哈希值
     */
    int hashCode();


//    下面三个方法涉及到stream流，暂时无法给出相应的解释。
//    就直接拉去过来了，防止影响后面的 抄 代码


    /**
     * Creates a {@link Spliterator} over the elements in this collection.
     * <p>
     * Implementations should document characteristic values reported by the
     * spliterator.  Such characteristic values are not required to be reported
     * if the spliterator reports {@link Spliterator#SIZED} and this collection
     * contains no elements.
     *
     * <p>The default implementation should be overridden by subclasses that
     * can return a more efficient spliterator.  In order to
     * preserve expected laziness behavior for the {@link #stream()} and
     * {@link #parallelStream()}} methods, spliterators should either have the
     * characteristic of {@code IMMUTABLE} or {@code CONCURRENT}, or be
     * <em><a href="Spliterator.html#binding">late-binding</a></em>.
     * If none of these is practical, the overriding class should describe the
     * spliterator's documented policy of binding and structural interference,
     * and should override the {@link #stream()} and {@link #parallelStream()}
     * methods to create streams using a {@code Supplier} of the spliterator,
     * as in:
     * <pre>{@code
     *     Stream<E> s = StreamSupport.stream(() -> spliterator(), spliteratorCharacteristics)
     * }</pre>
     * <p>These requirements ensure that streams produced by the
     * {@link #stream()} and {@link #parallelStream()} methods will reflect the
     * contents of the collection as of initiation of the terminal stream
     * operation.
     *
     * @return a {@code Spliterator} over the elements in this collection
     * @implSpec The default implementation creates a
     * <em><a href="Spliterator.html#binding">late-binding</a></em> spliterator
     * from the collections's {@code Iterator}.  The spliterator inherits the
     * <em>fail-fast</em> properties of the collection's iterator.
     * <p>
     * The created {@code Spliterator} reports {@link Spliterator#SIZED}.
     * @implNote The created {@code Spliterator} additionally reports
     * {@link Spliterator#SUBSIZED}.
     *
     * <p>If a spliterator covers no elements then the reporting of additional
     * characteristic values, beyond that of {@code SIZED} and {@code SUBSIZED},
     * does not aid clients to control, specialize or simplify computation.
     * However, this does enable shared use of an immutable and empty
     * spliterator instance (see {@link Spliterators#emptySpliterator()}) for
     * empty collections, and enables clients to determine if such a spliterator
     * covers no elements.
     * @since 1.8
     */
    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 0);
    }

    /**
     * Returns a sequential {@code Stream} with this collection as its source.
     *
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @return a sequential {@code Stream} over the elements in this collection
     * @implSpec The default implementation creates a sequential {@code Stream} from the
     * collection's {@code Spliterator}.
     * @since 1.8
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * Returns a possibly parallel {@code Stream} with this collection as its
     * source.  It is allowable for this method to return a sequential stream.
     *
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @return a possibly parallel {@code Stream} over the elements in this
     * collection
     * @implSpec The default implementation creates a parallel {@code Stream} from the
     * collection's {@code Spliterator}.
     * @since 1.8
     */
    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
}
