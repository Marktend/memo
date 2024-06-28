package com.sparta.memo.controller;


import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memosList = new HashMap<>();

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // RequestDto > Entity
        Memo memo = new Memo(requestDto);

        // Memo Max ID Check
        Long maxId = memosList.size() > 0 ? Collections.max(memosList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // Db 저장
        memosList.put(memo.getId(), memo);

        // Entity > ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;


    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        // Map > List
        List<MemoResponseDto> responseList = memosList.values().stream().map(MemoResponseDto::new).toList();

        return responseList;
    }
}