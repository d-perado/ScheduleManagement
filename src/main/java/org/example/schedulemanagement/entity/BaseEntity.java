package org.example.schedulemanagement.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class) // AuditingEntity
// 후킹(hooking) 해서 특정 코드를 실행할 수 있도록 하는 게 바로 EntityListener
@MappedSuperclass // 1.jpa가 테이블로 만들지 않는다. 2. 상속받는 엔티티의 테이블에 포함된다. 속성매핑상속
@NoArgsConstructor
public class BaseEntity {
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
