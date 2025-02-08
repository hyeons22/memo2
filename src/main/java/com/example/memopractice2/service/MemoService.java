package com.example.memopractice2.service;

import com.example.memopractice2.dto.MemoRequestDto;
import com.example.memopractice2.dto.MemoResponseDto;
import com.example.memopractice2.entity.Memo;
import com.example.memopractice2.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getTitle(), dto.getContent(), LocalDateTime.now(), LocalDateTime.now());
        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponseDto(savedMemo.getId(),savedMemo.getTitle(),savedMemo.getContent(),savedMemo.getCreatedAt(),savedMemo.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();
        List<MemoResponseDto> dtoList = new ArrayList<>();

        for (Memo memo : memos) {
            MemoResponseDto dto = new MemoResponseDto(memo.getId(), memo.getTitle(), memo.getContent(), memo.getCreatedAt(), memo.getModifiedAt());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("id가 맞는 메모가 없습니다.")
        );

        return new MemoResponseDto(memo.getId(),memo.getTitle(),memo.getContent(),memo.getCreatedAt(), memo.getModifiedAt());

    }

    @Transactional
    public MemoResponseDto update(Long memoId, MemoRequestDto dto) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("id가 맞는 메모가 없습니다.")
        );
        memo.update(dto.getTitle(),dto.getContent(), LocalDateTime.now());

        return new MemoResponseDto(memo.getId(),memo.getTitle(),memo.getContent(),memo.getCreatedAt(), memo.getModifiedAt());
    }

    public void deletebyId(Long memoId) {
        memoRepository.deleteById(memoId);
    }
}
