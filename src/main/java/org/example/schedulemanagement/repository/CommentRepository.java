package org.example.schedulemanagement.repository;

import org.example.schedulemanagement.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteByScheduleId(Long scheduleId);
}
