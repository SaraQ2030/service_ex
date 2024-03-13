package org.example.artical.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class Artical {
    @NotNull(message = "the id should not be empty")
    private int id;
    @NotNull(message = "the title should not be empty")
    @Length(max = 100, message = "Maximum length is 100 char")
    private String title;
    @NotEmpty(message = "the author should not be empty")
    @Length(min = 5,max = 20,message = "the length of author should be greater than 4 and less than 20 characters ")
    private String author;
    @NotNull(message = "the content should not be empty")
    @Length(min =200 , message ="the content should  be more than 200 char")
    private String content;
    @NotBlank  (message = "the category should not be empty")
    @Pattern(regexp = "^(politics|sports|technology)$",message = "the category must be either (politics , sports OR technology")
    private String category;
    @NotBlank(message = "the image url should not be empty")
    private String imageURL;
    @AssertFalse
    private boolean isPublished;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

}
