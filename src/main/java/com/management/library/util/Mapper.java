package com.management.library.util;

import com.management.library.entity.Author;
import com.management.library.entity.Book;
import com.management.library.entity.Category;
import com.management.library.entity.Publisher;
import com.management.library.vo.AuthorRecord;
import com.management.library.vo.BookRecord;
import com.management.library.vo.CategoryRecord;
import com.management.library.vo.PublisherRecord;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static List<BookRecord> bookModelToVo(List<Book> books) {

        return books.stream().map(vo -> {
            var bookVo = new BookRecord(vo.getId(), vo.getIsbn(), vo.getName(), vo.getSerialName(),
                    vo.getDescription());
            return bookVo;
        }).collect(Collectors.toList());
    }

    public static List<AuthorRecord> authorModelToVo(List<Author> authors) {

        return authors.stream().map(vo -> {
            var authorVo = new AuthorRecord(vo.getId(), vo.getName(), vo.getDescription());
            return authorVo;
        }).collect(Collectors.toList());

    }

    public static List<CategoryRecord> categoryModelToVo(List<Category> categories) {

        return categories.stream().map(vo -> {
            var categoryVo = new CategoryRecord(vo.getId(), vo.getName());
            return categoryVo;
        }).collect(Collectors.toList());

    }

    public static List<PublisherRecord> publisherModelToVo(List<Publisher> publishers) {

        return publishers.stream().map(vo -> {
            var publisherVo = new PublisherRecord(vo.getId(), vo.getName());
            return publisherVo;
        }).collect(Collectors.toList());

    }
}
