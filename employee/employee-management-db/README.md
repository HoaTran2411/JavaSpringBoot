### Cấu trúc thư mục
![image](./image/structure.PNG)

### Chi tiết về project
- Sử dụng `H2 database`, tạo file `data.sql` trong đường dẫn `main/resources`, tạo bảng mới `employee` và insert sẵn 1 số data (có chứa trường `avatar` kiểu Blob để chứa ảnh)
- Tạo các chức năng CRUD (view và API), xử lý 1 số validation client nhập vào và handle exception API
- Client gửi request form data chứa `multipart file`, xử lý chuyển về dạng byte array để lưu vào database
- Chưa xử lý: client tạo mới employee, trường email phải k trùng vs dữ liệu trong database và khi client update employee, trường email cũng k được trùng vs dữ liệu trong database (có thể giữ nguyên email cũ) - hôm nào có hứng sẽ update sau!

### Test project
1. Truy cập trang chủ
![image](./image/home.PNG)

2. Xem chi tiết 1 employee
![image](./image/viewDetail.PNG)

3. Xóa dữ liệu employee đó
![image](./image/afterDelete.PNG)

4. Update 1 employee (kích vào nút `edit`), sẽ hiện ra form cho phép edit:
![image](./image/update.PNG)

+ Sau khi edit, kết quả:
![image](./image/afterUpdate.PNG)

5. Thêm mới 1 employee: kích vào `add new Employee` trên thanh ba, sẽ hiện ra form cho phép thêm mới:
![image](./image/create.PNG)

+ validate khi người dùng nhập sai, phần update employee cũng tương tự:
![image](./image/validation.PNG)

+ Nhập đủ, đúng các trường, kết quả:
![image](./image/afterCreate.PNG)

6. Tìm kiếm theo `fullName` và `email`: Nhập dữ liệu cần tìm kiếm trên thanh ba, ấn `search` sẽ được kết quả:
![image](./image/search.PNG)