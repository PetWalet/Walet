package com.ssafy.service.external.service;

import com.ssafy.service.api.dto.FinAccountServiceDto;
import com.ssafy.service.external.dto.InquireTransactionHistoryDto;

import java.util.List;

public interface NHFintechService {
    FinAccountServiceDto.Response OpenFinAccountARS(FinAccountServiceDto.Request request); //핀어카운트 ARS 발급
//                           고객명         생년월일 yyyymmdd ,  전화번호        계좌번호         출금이체등록여부 Y , N
    String CheckOpenFinAccount(String Rgno, String BrdtBrno, String Tlno , String key); //핀-어카운트 ARS발급 확인
    void CloseFinAccount(String FinAcno , String Tlno , String BrdtBrno); //핀-어카운트 해지
    String InquireFinAccountStatus(String FinAcno , String Tlno , String BrdtBrno); //핀-어카운트 상태조회
    void DrawingTransfer2(String FinAcno , String Tram , String Vran , String DractOtlt , String MractOtlt); //실시간 출금이체
    String CheckOnDrawingTransfer2(String FinAcno , String Tram , String Vran , String OrtrYmd , String OrtrIsTuno); //실시간 출금이체 결과확인
    void ReceivedTransferFinAccount(String FinAcno , String Tram , String DractOtlt , String MractOtlt); //농협입금이체(핀-어카운트)
    String CheckOnReceivedTransfer(String FinAcno , String Bncd , String Acno , String Tram , String OrtrYmd , String OrtrIsTuno); //입금이체 결과확인
    String CheckDepositorFinAccount(String FinAcno , String BrdtBrno); //예금주 실명확인(핀-어카운트)
    String InquireDepositorFinAccount(String FinAcno); //예금주 조회(핀-어카운트)
    Long InquireBalance(String FinAcno); //잔액조회
    List<InquireTransactionHistoryDto.Response.REC> InquireTransactionHistory(String FinAcno , String Insymd , String Ineymd , String TrnsDsnc , String Lnsq , String PageNo , String Dmcnt); //거래내역조회

//    void P2PNVirtualAccountNumberRequest(); //(신규) P2P투자자및차입자 가상계좌발급
//    void P2PNVirtualAccountTerminate(); //(신규) P2P투자자/차입자 가상계좌 해지
}
