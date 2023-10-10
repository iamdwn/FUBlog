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

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPostEntity, Long> {
    List<BlogPostEntity> findByIsApproved(Boolean isApproved);

    List<BlogPostEntity> findByTitleLike(String title);

    //    public List<BlogPostEntity> getBlogPostEntitiesByTitle(String title, Pageable pageable);
    public Page<BlogPostEntity> getBlogPostEntitiesByTitleLikeAndIsApprovedIsTrueAndStatusIsTrue(String title, Pageable pageable);

//    List<BlogPostEntity> findAllByCategory(Long id);

    Page<BlogPostEntity> findByCategory(CategoryEntity category, Pageable pageable);


    @Query("SELECT bp FROM BlogPostEntity bp WHERE bp IN :blogPostPage ORDER BY bp.category.categoryName asc ")
    Page<BlogPostEntity> arrangeAscendingByCategoryName(@Param("blogPostPage") Page<BlogPostEntity> blogPostPage,
                                                       Pageable pageable);

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



    Optional<BlogPostEntity> findByPinnedIsTrue();

    //    @Query("SELECT e FROM BlogPostEntity e ORDER BY e.createdDate DESC")
    Page<BlogPostEntity> findAllByStatusIsTrueAndIsApprovedIsTrue(Pageable pageable);

    Page<BlogPostEntity> findAllByStatusTrueAndIsApprovedTrueOrderByCreatedDateDesc(Pageable pageable);

    Page<BlogPostEntity> findAllByStatusTrueAndIsApprovedTrueOrderByCreatedDateAsc(Pageable pageable);

    Page<BlogPostEntity> findAllByStatusTrueAndIsApprovedTrueOrderByModifiedDateDesc(Pageable pageable);

    Page<BlogPostEntity> findAllByStatusTrueAndIsApprovedTrueOrderByModifiedDateAsc(Pageable pageable);

    //    Page<BlogPostEntity> findAllByOrderByViewDesc(Pageable pageable);
    Page<BlogPostEntity> findAllByStatusTrueAndIsApprovedTrueOrderByViewDesc(Pageable pageable);

}
