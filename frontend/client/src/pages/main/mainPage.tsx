import { useEffect, useState } from 'react';
import PetsIcon from '@mui/icons-material/Pets';
import Card from '../../components/common/card';
import { DashboardCountData } from '../../interface/api/dashboardApiInterface';
import { DashBoardCountRepository } from '../../repository/dashboard/dashboardRepository';
import UserIcon from '../../components/Icons/usericon';
import AccountIcon from '../../components/Icons/accounticon';
import NoticeManage from '../../components/dashboard/noticeManage';
import TransactionGraph from '../../data_source/dashboard/transactionGraph';
import WeeklyTransactionGraph from '../../data_source/dashboard/weeklyTransactionGraph';

function MainPage() {
  const [countData, SetCountData] = useState<DashboardCountData>();

  useEffect(() => {
    (async () => {
      const data = await DashBoardCountRepository();
      if (data) {
        SetCountData(data);
      }
    })();
  }, []);

  return (
    <div className="ml-24 pl-4 pt-4 h-[89vh]">
      <p className="text-3xl font-do-hyeon">DashBoard</p>
      <div className="flex h-[12%]">
        <Card width="w-[22%]" height="h-full" styling="p-2">
          신규 계좌 발급
        </Card>
        <Card width="w-[47%]" height="h-full" styling="p-2">
          주간 신규 고객 현황
          <p className="text-3xl ml-2 mt-2">{countData?.newUser}명 </p>
        </Card>
        <Card width="w-[22%]" height="h-full" styling="p-2">
          총 거래량
        </Card>
      </div>

      <div className="flex mt-4 h-[38%]">
        <Card width="w-[22%]" height="h-[33vh]" styling="p-2">
          <p className="text-xl">시스템 개요</p>
          <div className="flex ml-8 mt-2 h-[6vh] align-middle">
            <div className="w-[70%] my-auto">
              <p className="text-2xl">{countData?.allUsers}</p>
              <p>총 고객 수</p>
            </div>
            <UserIcon styling="icon" />
          </div>
          <hr className="mt-4 border-2" />
          <div className="flex ml-8 mt-2 h-[6vh] align-middle">
            <div className="w-[70%] my-auto">
              <p className="text-2xl">{countData?.allUsers}</p>
              <p>일반 계좌 수</p>
            </div>
            <AccountIcon styling="icon w-14 h-14" />
          </div>
          <hr className="mt-4 border-2" />
          <div className="flex ml-8 mt-2 h-[6vh] align-middle">
            <div className="w-[70%] my-auto">
              <p className="text-2xl">{countData?.allUsers}</p>
              <p>반려 동물 계좌 수</p>
            </div>
            <PetsIcon style={{ fontSize: '3.5vw' }} />
          </div>
        </Card>
        <Card width="w-[47%]" height="h-[33vh]" styling="p-2">
          <TransactionGraph />
        </Card>
        <Card width="w-[22%]" height="h-[33vh]" styling="p-2">
          <WeeklyTransactionGraph />
        </Card>
      </div>

      <div className="flex mt-4 h-[28%]">
        <Card width="w-[22%]" height="h-[33vh]" styling="p-2">
          <p>카테고리별 매출</p>
        </Card>
        <Card width="w-[47%]" height="h-[33vh]" styling="p-2">
          <p>최근 거래 내역</p>
        </Card>
        <Card width="w-[22%]" height="h-[33vh]" styling="p-2">
          <NoticeManage />
        </Card>
      </div>
    </div>
  );
}

export default MainPage;
