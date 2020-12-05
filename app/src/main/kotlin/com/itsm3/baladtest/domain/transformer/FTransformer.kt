package com.itsm3.baladtest.domain.transformer

import io.reactivex.FlowableTransformer

abstract class FTransformer<T> : FlowableTransformer<T, T>