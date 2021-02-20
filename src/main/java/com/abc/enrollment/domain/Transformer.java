package com.abc.enrollment.domain;
import java.util.function.Function;

@FunctionalInterface
public interface Transformer<T> {
	<R> R transform(Function<T, R> f);
}