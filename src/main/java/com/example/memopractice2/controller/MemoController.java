package com.example.memopractice2.controller;

import com.example.memopractice2.dto.MemoRequestDto;
import com.example.memopractice2.dto.MemoResponseDto;
import com.example.memopractice2.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public MemoResponseDto save(@RequestBody MemoRequestDto dto){
        return memoService.save(dto);
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> findAll(){
        return memoService.findAll();
    }

    @GetMapping("/memos/{memoId}")
    public MemoResponseDto findById(@PathVariable Long memoId){
        return memoService.findById(memoId);
    }

    @PutMapping("/memos/{memoId}")
    public MemoResponseDto update(@PathVariable Long memoId, @RequestBody MemoRequestDto dto){
        return memoService.update(memoId,dto);
    }

    @DeleteMapping("/memos/{memoId}")
    public void deleteById(@PathVariable Long memoId){
        memoService.deletebyId(memoId);
    }

}
