package com.son.CapstoneProject.common.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.son.CapstoneProject.common.entity.login.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.lucene.analysis.charfilter.MappingCharFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
// As it is correctly suggested in previous answers, lazy loading means that when you
// fetch your object from the database, the nested objects are not fetched
// (and may be fetched later when required (LAZY load)).
// Now Jackson tries to serialize the nested object (== make JSON out of it), but fails as it
// finds JavassistLazyInitializer instead of normal object. To fix this, use the annotation below
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

// When the Hibernate SessionFactory bootstraps, Hibernate Search looks for all
// mapped entities marked as @Indexed and processes them
@Indexed

@AnalyzerDef(
        name = "customAnalyzer",
        charFilters = {
                // Same as ASCIIFoldingFilterFactory but we can specify mapping file
                @CharFilterDef(factory = MappingCharFilterFactory.class, params = {
                        @Parameter(
                                name = "mapping",
                                value = "mapping.txt")
                })
        },
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class)
        }
)
public class Question implements Serializable {

    @Id
    @GeneratedValue
    private Long questionId;

    // default: index=Index.YES, analyze=Analyze.YES and store=Store.NO
    @Analyzer(definition = "customAnalyzer")
    @Field(store = Store.YES)
    @Column(columnDefinition = "nvarchar(255)")
    private String title;

//    @Analyzer(definition = "customAnalyzer")
//    @Field(store = Store.YES)
    @Column(columnDefinition = "ntext")
    private String content;

    @Analyzer(definition = "customAnalyzer")
    @Field(store = Store.YES)
    @Column(columnDefinition = "ntext")
    private String contentWithoutHtmlTags; // contain no html tags

    // Many questions can be asked by an user
//    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_QUESTION_APPUSER"))
    private AppUser appUser;

    // One question can have many answers
    // @JsonIgnore to ignore this field when parsing the request body to this class (deserialization)
    // "@JsonIgnore is used to ignore the logical property used IN SERIALIZATION AND DESERIALIZATION"
//    @JsonIgnore
    // @JsonManagedReference means this list is shown in response,
    // and @JsonBackReference (for a single object) means
    // this will not be shown in response (avoid recursive)
    // @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    private int viewCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<UploadedFile> uploadedFiles;

//    @JsonManagedReference
    @IndexedEmbedded
    @ManyToMany(fetch = FetchType.LAZY/*, cascade = CascadeType.ALL*/)
    private List<Tag> tags;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date utilTimestamp;

    // Many edited versions for this question by other users
//    @JsonManagedReference
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<EditedQuestion> editedQuestions;

//    @JsonManagedReference
//    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Comment> comments;

    @ElementCollection
    private List<Long> upvotedUserIds;

    private Integer upvoteCount;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Report> reports;

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppUser> subscribers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionId, question.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId);
    }
}
