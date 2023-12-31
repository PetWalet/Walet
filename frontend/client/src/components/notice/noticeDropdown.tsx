import { Fragment, useState } from 'react';
import { Menu, Transition } from '@headlessui/react';
import { useModal } from '../modal/modalClass';
import { ActiveModal, DeleteModal } from '../modal/customModal';

function NoticeDropdown({ pageid }: { pageid: string }) {
  const { openModal } = useModal();
  const [active, setActive] = useState(0);
  const [deleted, setDeleted] = useState(0);

  const handleUpdate = (id: string) => {
    window.location.href = `/notice/update/${id}`;
  };

  const handleActive = () => {
    setDeleted(0);
    setActive(1);
    openModal('active');
  };

  const handleDelete = () => {
    setActive(0);
    setDeleted(1);
    openModal('delete');
  };

  return (
    <Menu as="div" className="relative w-8 ml-2 inline-block text-left">
      <div>
        <Menu.Button className="inline-flex w-full rounded-lg justify-center gap-x-1.5 hover:rounded-t-md bg-gray-300 px-3 py-2 text-sm font-semibold text-gray-900 shadow-lg hover:ring-1 hover:ring-inset hover:ring-green-300 hover:bg-green-300">
          +
        </Menu.Button>
      </div>

      <Transition
        as={Fragment}
        enter="transition ease-out duration-100"
        enterFrom="transform opacity-0 scale-95"
        enterTo="transform opacity-100 scale-100"
        leave="transition ease-in duration-75"
        leaveFrom="transform opacity-100 scale-100"
        leaveTo="transform opacity-0 scale-95">
        <Menu.Items className="absolute rounded-lg left-0 right-0 z-10  w-28 origin-top-right rounded-b-md bg-BACKGROUND-50 shadow-lg">
          <div>
            <Menu.Item>
              <button
                type="button"
                className="w-full bg-gray-100 text-gray-900 block px-4 py-2 text-sm hover:bg-green-100"
                onClick={() => handleActive()}>
                활성화하기
              </button>
            </Menu.Item>
            <Menu.Item>
              <button
                type="button"
                className="w-full bg-gray-100 text-gray-900 block px-4 py-2 text-sm hover:bg-green-100"
                onClick={() => handleUpdate(pageid)}>
                수정하기
              </button>
            </Menu.Item>
            <Menu.Item>
              <button
                type="button"
                className="w-full bg-gray-100 text-gray-900 block px-4 py-2 text-sm hover:bg-green-100"
                onClick={() => handleDelete()}>
                삭제하기
              </button>
            </Menu.Item>
          </div>
        </Menu.Items>
      </Transition>

      {deleted === 1 && (
        <DeleteModal content="정말로 삭제하시겠습니까?" id={pageid} />
      )}

      {active === 1 && (
        <ActiveModal content="메인 공지사항으로 설정하겠습니까?" id={pageid} />
      )}
    </Menu>
  );
}

export default NoticeDropdown;
