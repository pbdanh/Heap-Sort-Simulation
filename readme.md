# Heap Sort Simulation

Thuật toán Heap Sort được dùng dể sắp xếp dữ liệu đầu vào theo thứ tự

Heap Sort Simulation là một chương trình cơ bản sử dụng javafx để mô phỏng lại quá trình thực hiện việc Heap Sort

![](/preview/preview3.png)

[Cài đặt]()  
[Cách sử dụng]()  
[Thuật toán Heap Sort]()

## Cài đặt

Yêu cầu máy tính phải được cài đặt maven

Chạy câu lệnh sau để thực thi chương trình

```bash
mvn clean javafx:run
```

Hoặc có thể sử dụng IDE Intellij mở thư mục chứa code

Chọn View -> Tool Windows -> Maven để hiện lên công cụ Maven

Chọn HeapSort->javafx và kích đúp vào javafx:run để chạy chương trình

## Cách sử dụng

![](/preview/preview1.png)

R: chọn ngẫu nhiên một dãy

ENTER: thực hiện việc sắp xếp dãy

![](/preview/preview2.png)

## Thuật toán Heap Sort

Cấu trúc đống nhị phân là trái tim của thuật toán. Cấu trúc này là một dạng đặc biệt của cây nhị phân hoàn chỉnh trong đó node con được so sánh với node cha và được sắp xếp một cách phù hợp.

Trong chương trình trên cấu trúc heap được sử dụng là Max heap: khóa của node cha luôn lớn hơn node node con.

Các bước để thực hiện Heap Sort:

- Ta coi dãy cần sắp xếp là một cây nhị phân hoàn chỉnh, sau đó hiệu chỉnh cây thành dạng cấu trúc heap (vun đống).
- Dựa vào tính chất của cấu trúc heap, ta có thể lấy được ra phần từ lớn nhất của dãy, phần tử này chính là gốc của heap. Đưa phần tử này về đúng vị trí của dãy ở cuối mảng sau đó giảm số lượng phần tử của cây nhị phân và tái cấu trúc heap. Lặp lại bước này đến khi danh sách chỉ còn 1 phần tử.

Đánh giá thuật toán:

Trong mọi trường hợp độ phức tạp của nó luôn là O(nlog(n))

Ưu điểm:
- Có độ phức tạp O(nlog(n)) trong mọi trường hợp, là một trong những thuật toán sắp xếp nhanh nhất
- Ít bị ảnh hưởng bởi dữ liệu đầu vào, ứng dụng nhiều trong thực tế

Nhược điểm:
- Cài đặt phức tạp, khó khăn trong việc tìm hiểu thuật toán
- Không tối ưu trong mọi trường hợp
