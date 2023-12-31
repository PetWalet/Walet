package com.ssafy.service.external.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.service.external.client.NHFintechClient;
import com.ssafy.service.external.client.OauthClient;
import com.ssafy.service.external.dto.*;
import com.ssafy.service.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class NHFintechServiceImpl implements NHFintechService{
    private final NHFintechClient nhFintechClient;
    private final OauthService oauthService;
    private final ObjectMapper objectMapper;
    private final TimeUtil timeUtil;

    @Override
    public OpenFinAccountARSDto.Response OpenFinAccountARS(OpenFinAccountARSDto.Request data) {
        HeaderDto header = getHeader("OpenFinAccountARS");
        OpenFinAccountARSDto.Request request = OpenFinAccountARSDto.Request.builder()
                .Header(header)
                .Csnm(data.getCsnm())
                .BrdtBrno(data.getBrdtBrno())
                .Tlno(data.getTlno())
                .Acno(data.getAcno())
                .DrtrRgyn(data.getDrtrRgyn())
                .build();
        String jsonString = nhFintechClient.OpenFinAccountARS("Basic " + oauthService.getOauthKey() , request);
        try {
            OpenFinAccountARSDto.Response response = objectMapper.readValue(jsonString , OpenFinAccountARSDto.Response.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public CheckOpenFinAccountDto.Response CheckOpenFinAccount(CheckOpenFinAccountDto.Request data) {
        HeaderDto header = getHeader("CheckOpenFinAccount");
        CheckOpenFinAccountDto.Request request = CheckOpenFinAccountDto.Request.builder()
                .Header(header)
                .Rgno(data.getRgno())
                .BrdtBrno(data.getBrdtBrno())
                .Tlno(data.getTlno())
                .build();
        String jsonString = nhFintechClient.CheckOpenFinAccount("Basic " + oauthService.getOauthKey(), request);
        try {
            return objectMapper.readValue(jsonString , CheckOpenFinAccountDto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FinAccountDto.Response CloseFinAccount(FinAccountDto.Request data) {
        HeaderDto header = getHeader("CloseFinAccount");
        FinAccountDto.Request request = FinAccountDto.Request.builder()
                .Header(header)
                .FinAcno(data.getFinAcno())
                .Tlno(data.getTlno())
                .BrdtBrno(data.getBrdtBrno())
                .build();
        String jsonString = nhFintechClient.CloseFinAccount("Basic " + oauthService.getOauthKey(), request);
        try {
            return objectMapper.readValue(jsonString , FinAccountDto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FinAccountDto.Response InquireFinAccountStatus(FinAccountDto.Request data) {
        HeaderDto header = getHeader("InquireFinAccountStatus");
        FinAccountDto.Request request = FinAccountDto.Request.builder()
                .Header(header)
                .FinAcno(data.getFinAcno())
                .Tlno(data.getTlno())
                .BrdtBrno(data.getBrdtBrno())
                .build();
        String jsonString = nhFintechClient.InquireFinAccountStatus("Basic " + oauthService.getOauthKey() , request);
        try {
            return objectMapper.readValue(jsonString , FinAccountDto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DrawingTransfer2Dto.Response DrawingTransfer2(DrawingTransfer2Dto.Request data) {
        HeaderDto header = getHeader("DrawingTransfer2");
        DrawingTransfer2Dto.Request request = DrawingTransfer2Dto.Request.builder()
                .Header(header)
                .FinAcno(data.getFinAcno())
                .Tram(data.getTram())
                .Vran(data.getVran())
                .DractOtlt(data.getDractOtlt())
                .MractOtlt(data.getMractOtlt())
                .build();
        String jsonString = nhFintechClient.DrawingTransfer2("Basic " + oauthService.getOauthKey() , request);

        try {
            log.info("출금 finAccount = {} , 가상계좌 = {} , 금액 = {} , 출금인자 = {} , 입금인자 = {}",data.getFinAcno(), data.getVran() , data.getTram() , data.getDractOtlt() , data.getMractOtlt());
            return objectMapper.readValue(jsonString , DrawingTransfer2Dto.Response.class);
        } catch (JsonProcessingException e) {
            log.error("출금오류 finAccount = {} , 가상계좌 = {} , 금액 = {} , 출금인자 = {} , 입금인자 = {} " , data.getFinAcno(), data.getVran() , data.getTram() , data.getDractOtlt() , data.getMractOtlt());
            throw new RuntimeException(e);
        }
    }

    @Override
    public CheckOnDrawingTransfer2Dto.Response CheckOnDrawingTransfer2(CheckOnDrawingTransfer2Dto.Request data) {
        HeaderDto header = getHeader("CheckOnDrawingTransfer2Dto");
        CheckOnDrawingTransfer2Dto.Request request = CheckOnDrawingTransfer2Dto.Request.builder()
                .Header(header)
                .FinAcno(data.getFinAcno())
                .Tram(data.getTram())
                .Vran(data.getVran())
                .OrltYmd(data.getOrltYmd())
                .OrtrIsTuno(data.getOrtrIsTuno())
                .build();
        String jsonString = nhFintechClient.CheckOnDrawingTransfer2("Basic " + oauthService.getOauthKey(),request);
        try {
            return objectMapper.readValue(jsonString , CheckOnDrawingTransfer2Dto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ReceivedTransferFinAccountDto.Response ReceivedTransferFinAccount(ReceivedTransferFinAccountDto.Request data) {
        HeaderDto header = getHeader("ReceivedTransferFinAccount");
        ReceivedTransferFinAccountDto.Request request = ReceivedTransferFinAccountDto.Request.builder()
                .Header(header)
                .FinAcno(data.getFinAcno())
                .Tram(data.getTram())
                .DractOtlt(data.getDractOtlt())
                .MractOtlt(data.getMractOtlt())
                .build();
        String jsonString = nhFintechClient.ReceivedTransferFinAccount("Basic " + oauthService.getOauthKey(),request);
        try {
            log.info("입금 finAccount = {} , 금액 = {} , 출금인자 = {} , 입금인자 = {}",data.getFinAcno() , data.getTram() , data.getDractOtlt() , data.getMractOtlt());
            return objectMapper.readValue(jsonString , ReceivedTransferFinAccountDto.Response.class);
        } catch (JsonProcessingException e) {
            log.error("입금오류 : finAccount = {} , 금액 = {} , 출금인자 = {} , 입금인자 = {}",data.getFinAcno() , data.getTram() , data.getDractOtlt() , data.getMractOtlt());
            throw new RuntimeException(e);
        }
    }

    @Override
    public CheckOnReceivedTransferDto.Response CheckOnReceivedTransfer(CheckOnReceivedTransferDto.Request data) {
        HeaderDto header = getHeader("CheckOnReceivedTransfer");
        CheckOnReceivedTransferDto.Request request = CheckOnReceivedTransferDto.Request.builder()
                .Header(header)
                .FinAcno(data.getFinAcno())
                .Bncd(data.getBncd())
                .Acno(data.getAcno())
                .Tram(data.getTram())
                .OrtrYmd(data.getOrtrYmd())
                .OrtrIsTuno(data.getOrtrIsTuno())
                .build();
        String jsonString = nhFintechClient.CheckOnReceivedTransfer("Basic " + oauthService.getOauthKey(),request);
        try {
            return objectMapper.readValue(jsonString , CheckOnReceivedTransferDto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CheckDepositorFinAccountDto.Response CheckDepositorFinAccount(CheckDepositorFinAccountDto.Request data) {
        HeaderDto header = getHeader("CheckDepositorFinAccount");
        CheckDepositorFinAccountDto.Request request = CheckDepositorFinAccountDto.Request.builder()
                .Header(header)
                .FinAcno(data.getFinAcno())
                .BrdtBrno(data.getBrdtBrno())
                .build();
        String jsonString = nhFintechClient.CheckDepositorFinAccount("Basic " + oauthService.getOauthKey(),request);
        try {
            return objectMapper.readValue(jsonString , CheckDepositorFinAccountDto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InquireDepositorFinAccountDto.Response InquireDepositorFinAccount(InquireDepositorFinAccountDto.Request data) {
        HeaderDto header = getHeader("InquireDepositorFinAccount");
        InquireDepositorFinAccountDto.Request request = InquireDepositorFinAccountDto.Request.builder()
                .Header(header)
                .FinAcno(data.getFinAcno())
                .build();

//        String jsonString = null;
//        try {
//            jsonString = nhFintechClient.InquireDepositorFinAccount("Basic " + oauthService.getOauthKey(),objectMapper.writeValueAsString(request).getBytes());
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        String jsonString = nhFintechClient.InquireDepositorFinAccount("Basic " + oauthService.getOauthKey(),request);
        try {
            return objectMapper.readValue(jsonString , InquireDepositorFinAccountDto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InquireBalanceDto.Response InquireBalance(InquireBalanceDto.Request data) {
        HeaderDto header = getHeader("InquireBalance");
        InquireBalanceDto.Request request = InquireBalanceDto.Request.builder()
                .Header(header)
                .FinAcno(data.getFinAcno())
                .build();
        String jsonString = nhFintechClient.InquireBalance("Basic " + oauthService.getOauthKey(), request);
        try {
            return objectMapper.readValue(jsonString , InquireBalanceDto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InquireTransactionHistoryDto.Response InquireTransactionHistory(InquireTransactionHistoryDto.Request data) {
        HeaderDto header = getHeader("InquireTransactionHistory");
        InquireTransactionHistoryDto.Request request = InquireTransactionHistoryDto.Request.builder()
                .Header(header)
                .FinAcno(data.getFinAcno())
                .Insymd(data.getInsymd())
                .Ineymd(data.getIneymd())
                .TrnsDsnc(data.getTrnsDsnc())
                .Lnsq(data.getLnsq())
                .PageNo(data.getPageNo())
                .Dmcnt(data.getDmcnt())
                .build();
        String jsonString = nhFintechClient.InquireTransactionHistory("Basic " + oauthService.getOauthKey() , request);
        try {
            return objectMapper.readValue(jsonString , InquireTransactionHistoryDto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public P2PNVirtualAccountNumberRequestDto.Response P2PNVirtualAccountNumberRequest(P2PNVirtualAccountNumberRequestDto.Request data) {
        HeaderDto header = getHeader("P2PNVirtualAccountNumberRequest");
        P2PNVirtualAccountNumberRequestDto.Request request = P2PNVirtualAccountNumberRequestDto.Request.builder()
                .Header(header)
                .P2pVractUsg("1")
                .InvstBrwNm(data.getInvstBrwNm())
                .DwmBncd(data.getDwmBncd())
                .DwmAcno(data.getDwmAcno())
                .Mnamt(data.getMnamt())
                .MinAmt(data.getMinAmt())
                .RpayTrnsfYn(data.getRpayTrnsfYn())
                .build();
        String jsonString = nhFintechClient.P2PNVirtualAccountNumberRequest("Basic " + oauthService.getOauthKey() , request);
        try {
            return objectMapper.readValue(jsonString , P2PNVirtualAccountNumberRequestDto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public P2PNVirtualAccountTerminateDto.Response P2PNVirtualAccountTerminate(P2PNVirtualAccountTerminateDto.Request data) {
        HeaderDto header = getHeader("P2PNVirtualAccountTerminate");
        P2PNVirtualAccountTerminateDto.Request request = P2PNVirtualAccountTerminateDto.Request.builder()
                .Header(header)
                .Vran(data.getVran())
                .build();
        String jsonString = nhFintechClient.P2PNVirtualAccountTerminate("Basic " + oauthService.getOauthKey() , request);
        try {
            return objectMapper.readValue(jsonString , P2PNVirtualAccountTerminateDto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public P2PNInvestmentManagementVirtualAccountListDto.Response P2PNInvestmentManagementVirtualAccountList(P2PNInvestmentManagementVirtualAccountListDto.Request data) {
        HeaderDto header = getHeader("P2PNInvestmentManagementVirtualAccountList");
        P2PNInvestmentManagementVirtualAccountListDto.Request request = P2PNInvestmentManagementVirtualAccountListDto.Request.builder()
                .Header(header)
                .P2pVractUsg(data.getP2pVractUsg())
                .Vran(data.getVran())
                .Lnsq(data.getLnsq())
                .PageNo(data.getPageNo())
                .build();
        String jsonString = nhFintechClient.P2PNInvestmentManagementVirtualAccountList("Basic " + oauthService.getOauthKey() , request);
        try {
            return objectMapper.readValue(jsonString , P2PNInvestmentManagementVirtualAccountListDto.Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HeaderDto getHeader(String ApiNm) {
        return HeaderDto.builder()
                .ApiNm(ApiNm)
                .Tsymd(timeUtil.YMD(LocalDateTime.now()))
                .Trtm(timeUtil.HMS(LocalDateTime.now()))
                .Iscd("000449")
                .FintechApsno("001")
                .ApiSvcCd("01E_024_00")
                .IsTuno(timeUtil.YMDHMS(LocalDateTime.now()))
                .build();
    }
}
