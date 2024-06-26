package com.hhplus.architecture.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.hhplus.architecture.common.exception.DataNotFoundException;
import com.hhplus.architecture.repository.ApplyCounterJpaRepository;
import com.hhplus.architecture.repository.LectureJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class LectureManagerTest {

  @Mock
  private LectureJpaRepository lectureJpaRepository;

  @Mock
  private ApplyCounterJpaRepository applyCounterJpaRepository;

  @InjectMocks
  private LectureManagerImpl lectureManager;

  /**
   * 특강를 못찾을 경우 exception 발생.
   */
  @Test
  void findById_notFoundException() {
    // given
    // when
    when(applyCounterJpaRepository.findAndLockByLectureId(anyLong()))
        .thenReturn(Optional.empty());

    // then
    assertThatThrownBy(() -> lectureManager.findCountAndLockByLectureId(1L))
        .isInstanceOf(DataNotFoundException.class)
        .hasMessage("강의가 등록되지 않았습니다.");
  }

}