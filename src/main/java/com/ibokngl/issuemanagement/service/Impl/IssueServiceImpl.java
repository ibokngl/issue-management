package com.ibokngl.issuemanagement.service.Impl;

import com.ibokngl.issuemanagement.dto.IssueDto;
import com.ibokngl.issuemanagement.entity.Issue;
import com.ibokngl.issuemanagement.repository.IssueRepository;
import com.ibokngl.issuemanagement.service.IssueService;
import com.ibokngl.issuemanagement.util.Tpage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper)
    {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public IssueDto save(IssueDto issue) {
        if (issue.getDate()==null)
            throw new IllegalArgumentException("Issue Date cannot be null");

        Issue issueDb = modelMapper.map(issue,Issue.class);
        issueDb = issueRepository.save(issueDb);
        return modelMapper.map(issueDb,IssueDto.class);
    }

    @Override
    public IssueDto getById(Long Id) {
        Issue issue = issueRepository.getOne(Id);
        return modelMapper.map(issue, IssueDto.class);
    }

    @Override
    public Tpage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data=issueRepository.findAll(pageable);
        Tpage page=new Tpage<IssueDto>();
        IssueDto[] dtos=modelMapper.map(data.getContent(),IssueDto[].class);
        page.setStat(data, Arrays.asList(dtos));
        return page;
    }

    @Override
    public Boolean delete(Long issueId) {
       issueRepository.deleteById(issueId);
        return true;
    }

    @Override
    public IssueDto update(Long id, IssueDto project) {
        return null;
    }
}

    // Injection yapmamızın sebei save metodunu kullanmak içindir


