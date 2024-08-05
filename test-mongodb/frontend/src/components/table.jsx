import React from "react";
import { Table, Empty } from "antd";
import { SmileOutlined } from "@ant-design/icons";

// Data untuk tabel
const data = []; // Gantilah dengan data nyata jika ada

const columns = [
  {
    title: "Nama",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "Email",
    dataIndex: "email",
    key: "email",
  },
  {
    title: "Gender",
    dataIndex: "gender",
    key: "gender",
  },
];

const MyTable = () => {
  return (
    <Table
      columns={columns}
      dataSource={data}
      locale={{
        emptyText: (
          <Empty
            description="Data tidak ditemukan"
            image={<SmileOutlined style={{ fontSize: 48 }} />}
          />
        ),
      }}
      rowKey="email" // Pastikan setiap baris memiliki key unik
    />
  );
};

export default MyTable;
