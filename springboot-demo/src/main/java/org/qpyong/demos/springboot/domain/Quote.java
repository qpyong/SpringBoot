package org.qpyong.demos.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用于接收请求结果（restful webservice的请求结果通常都是json格式）的实体。
 * 属性名必须要和请求结果中的属性完全一致。当然，如果不完全一致，则需要使用<code>@JsonProperty</code>来指定映射。
 * <p>
 * <code>@JsonIgnoreProperties</code> 用于忽略处理结果中没有绑定到该实体的属性
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    private String type;
    private Value value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
