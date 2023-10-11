package tech.fublog.FuBlog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.fublog.FuBlog.dto.BlogPostDTO;
import tech.fublog.FuBlog.entity.BlogPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.fublog.FuBlog.entity.CategoryEntity;
import tech.fublog.FuBlog.entity.TagEntity;
import tech.fublog.FuBlog.projection.BlogPostProjection;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPostEntity, Long> {
    List<BlogPostEntity> findByIsApproved(Boolean isApproved);

    List<BlogPostEntity> findByTitleLike(String title);

    public Page<BlogPostEntity> getBlogPostEntitiesByTitleLikeAndIsApprovedIsTrueAndStatusIsTrue(String title, Pageable pageable);

    Page<BlogPostEntity> findByCategory(CategoryEntity category, Pageable pageable);


    @Query("SELECT bp FROM BlogPostEntity bp WHERE bp.category IN:categoryEntityList AND " +
            "bp.isApproved = true AND bp.status = true ORDER BY bp.category.categoryName asc ")
    Page<BlogPostEntity> findByCategoryInAndIsApprovedTrueAndStatusTrue(@Param("categoryEntityList") List<CategoryEntity> categoryEntityList,
                                                                        Pageable pageable);

//    @Query("SELECT bp FROM BlogPostEntity bp WHERE (bp.category.id = :categoryId OR bp.category.parentCategory.id = :categoryId) " +
//            "AND (bp.isApproved = true AND bp.status = true) ORDER BY bp.category.categoryName asc ")
//    Page<BlogPostEntity> findBlogPostsByCategoryIdOrParentId(
//            @Param("categoryId") Long categoryId,
//            Pageable pageable
//    );


    BlogPostEntity findByPinnedIsTrue();

    //    @Query("SELECT e FROM BlogPostEntity e ORDER BY e.createdDate DESC")
    Page<BlogPostEntity> findAllByStatusIsTrueAndIsApprovedIsTrue(Pageable pageable);

    Page<BlogPostEntity> findAllByStatusTrueAndIsApprovedTrueOrderByCreatedDateDesc(Pageable pageable);

//    @Query("SELECT b.id AS id, b.createdDate AS createdDate FROM BlogPostEntity b WHERE b.status = true AND b.isApproved = true ORDER BY b.createdDate DESC")
//    Page<BlogPostProjection> findAllByStatusTrueAndIsApprovedTrueOrderByCreatedDateDesc(Pageable pageable);

//    @Query("SELECT b.id AS id, b.title AS title, b.createdDate AS createdDate FROM BlogPostEntity b")
//    Page<BlogPostProjection> findSelectedFields(Pageable pageable);
    Page<BlogPostEntity> findAllByStatusTrueAndIsApprovedTrueOrderByCreatedDateAsc(Pageable pageable);

    Page<BlogPostEntity> findAllByStatusTrueAndIsApprovedTrueOrderByModifiedDateDesc(Pageable pageable);

    Page<BlogPostEntity> findAllByStatusTrueAndIsApprovedTrueOrderByModifiedDateAsc(Pageable pageable);

    Page<BlogPostEntity> findAllByStatusTrueAndIsApprovedTrueOrderByViewDesc(Pageable pageable);

    List<BlogPostEntity> findByPostTagsTag(TagEntity tag);

    @Query("SELECT bp FROM BlogPostEntity bp WHERE bp.status = true AND bp.isApproved = true ORDER BY bp.view DESC LIMIT 6")
    List<BlogPostEntity> findAllByStatusTrueAndIsApprovedTrueOrderByViewDesc();
}
