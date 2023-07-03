package com.deercorp.blackcompany.post;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String title;
    private String content;
}
