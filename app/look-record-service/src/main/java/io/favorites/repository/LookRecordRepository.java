package io.favorites.repository;

import io.favorites.domain.LookRecord;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;


@RepositoryRestResource(path = "lookRecords", collectionResourceRel = "data")
public interface LookRecordRepository extends CrudRepository<LookRecord, Long> {

    /**
     * 查询用户浏览历史记录
     */
//    public String userLookRecordSql = "select c.id as id,c.title as title, c.type as type,c.url as url,c.logoUrl as logoUrl,c.userId as userId, "
//            + "c.remark as remark,c.description as description,c.lastModifyTime as lastModifyTime,r.lastModifyTime as createTime, "
//            + "u.userName as userName,u.profilePicture as profilePicture,f.id as favoritesId,f.name as favoriteName "
//            + "from LookRecord r,Collect c,User u,Favorites f "
//            + "WHERE c.userId = u.id and r.collect.id = c.id and c.favoritesId=f.id and c.isDelete='NO'";

//    @Query(userLookRecordSql+ " and r.user.id=?1")
//    Page<CollectOfLookRecord> findViewByUserId(@Param(value = "userId")Long userId, Pageable pageable);

    Integer countByUserIdAndCollectId(@Param(value = "userId")Long userId, @Param(value = "collectId")Long collectId);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update LookRecord l set l.lastModifyTime=:lastModifyTime where l.user.id=:userId and l.collect.id=:collectId")
    void updatelastModifyTime(@Param("userId") Long userId, @Param("collectId") Long collectId, @Param("lastModifyTime") Long lastModifyTime);

    @Transactional
    Long deleteByUserIdAndCollectId(@Param(value = "userId")Long userId, @Param(value = "collectId")Long collectId);

    @Transactional
    Long deleteByUserId(@Param(value = "userId")Long userId);

//    @Transactional
//    LookRecord save(@RequestBody LookRecord lookRecord);
}
