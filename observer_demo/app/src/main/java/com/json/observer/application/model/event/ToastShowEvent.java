package com.json.observer.application.model.event;

import lombok.Getter;

/**
 * Created by json on 16/2/2.
 */
public class ToastShowEvent {
    @Getter
    private String content;

    public ToastShowEvent(String content) {
        this.content = content;
    }
}
