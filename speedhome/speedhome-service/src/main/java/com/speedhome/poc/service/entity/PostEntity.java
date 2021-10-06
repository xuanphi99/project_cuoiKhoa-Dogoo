package com.speedhome.poc.service.entity;

import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Indexed
@Entity
@Table(name = "POST")
@AnalyzerDef(name = "postAnalyzer",
        tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class ),
        filters = {
                @TokenFilterDef(factory = StandardFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        }
)
public class PostEntity {
        @Id
        @Column(name = "ID_POST", nullable = false)
        private String idPost;

        @Column(name = "IMG_NAME", nullable = false)
        private String imgName;

        @Column(name = "IMG_URL", nullable = false)
        private String imgURL;

        @Field(analyzer = @Analyzer(definition = "postAnalyzer"))
        @Column(name = "TITLE", nullable = false)
        private String title = null;

        @Column(name = "CREATE_DATE", nullable = true)
        private LocalDate createDate = null;

        @Column(name = "CREATE_BY", nullable = true)
        private String createBy = null;

        @Column(name = "MODIFY_DATE", nullable = true)
        private LocalDate modifyDate = null;

        @Column(name = "MODIFY_BY", nullable = true)
        private String modifyBy = null;

        @Field(analyzer = @Analyzer(definition = "postAnalyzer"))
        @Column(name = "DESCRIPTION", nullable = false)
        private String description = null;

        @Field(analyzer = @Analyzer(definition = "postAnalyzer"))
        @Column(name = "CONTENT", nullable = false)
        private String content = null;

        @Column(name = "CATEGORY_ID", nullable = false)
        private String categoryId = null;

        public String getIdPost() {
                return idPost;
        }

        public void setIdPost(String idPost) {
                this.idPost = idPost;
        }

        public String getImgName() {
                return imgName;
        }

        public void setImgName(String imgName) {
                this.imgName = imgName;
        }

        public String getImgURL() {
                return imgURL;
        }

        public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public LocalDate getCreateDate() {
                return createDate;
        }

        public void setCreateDate(LocalDate createDate) {
                this.createDate = createDate;
        }

        public String getCreateBy() {
                return createBy;
        }

        public void setCreateBy(String createBy) {
                this.createBy = createBy;
        }

        public LocalDate getModifyDate() {
                return modifyDate;
        }

        public void setModifyDate(LocalDate modifyDate) {
                this.modifyDate = modifyDate;
        }

        public String getModifyBy() {
                return modifyBy;
        }

        public void setModifyBy(String modifyBy) {
                this.modifyBy = modifyBy;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public String getCategoryId() {
                return categoryId;
        }

        public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
        }
}
