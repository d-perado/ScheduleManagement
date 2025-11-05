package org.example.schedulemanagement.repository;

import org.example.schedulemanagement.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteByScheduleId(Long scheduleId);

    List<Comment> findByScheduleId(Long scheduleId);
}
