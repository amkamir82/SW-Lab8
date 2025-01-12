# SW-Lab-8 (پیمایشگر گراف)

در این پروژه، یک پیمایشگر ساده گراف با دو روش اول-سطح و اول-عمق پیاده سازی شده است.


 تلاش می‌کنیم وابستگی کلاس‌های پیمایش (DFS و BFS) به نوع خاصی از گراف (مثلاً SparseMultigraph از کتابخانه Jung) را از بین ببریم. برای این کار:

1. یک واسط (Interface) با نام Graph ایجاد کرده‌ایم.
2. یک کلاس آداپتر (GraphAdapter) نوشته‌ایم تا درخواست‌ها را از واسط Graph به کلاس اصلی SparseMultigraph هدایت کند.
3. کلاس‌های BfsGraphTraverser و DfsGraphTraverser را به‌گونه‌ای تغییر داده‌ایم که فقط به واسط Graph وابسته باشند، نه پیاده‌سازی خاص کتابخانه.
4. در کلاس Main از آداپتر استفاده می‌کنیم تا ساخت گراف و پیمایش آن انجام شود.

## 1. ایجاد واسط Graph
در ابتدا، واسطی به نام Graph تعریف می‌کنیم که نشان می‌دهد گراف ما باید چه متدهایی را ارائه دهد:

عکس از پیاده سازی گراف


- V نوع رأس‌ها (Vertices) را مشخص می‌کند (مثلاً `Integer`).
- E نوع یال‌ها (Edges) را مشخص می‌کند (مثلاً `String`).
- متدهای addVertex, addEdge و getNeighbors نیازهای اساسی ما برای کار با گراف هستند.

---

## 2. پیاده‌سازی آداپتر GraphAdapter

در این مرحله، کلاس GraphAdapter را می‌نویسیم که واسط <Graph<Integer, String را پیاده‌سازی می‌کند. این کلاس درون خود یک شئ از نوع <SparseMultigraph<Integer, String دارد و فراخوانی‌های واسط Graph را به آن منتقل می‌کند.

image

- شیء adaptee همان گراف اصلی Jung است.  
- هر متد واسط Graph را فراخوانی می‌کنیم و درخواست مربوطه را به adaptee منتقل می‌کنیم.  
- این ساختار نمونه‌ای از Object Adapter است، چون از ترکیب استفاده می‌کند (دارا بودن یک شیء از کلاس دیگر)، نه ارث‌بری.

---
## ۳. تغییر کلاس‌های پیمایش (DFS و BFS) برای استفاده از Graph

در این گام، کلاس‌های BfsGraphTraverser و DfsGraphTraverser به‌جای ارجاع مستقیم به SparseMultigraph`، به واسط `<Graph<Integer, String وابسته می‌شوند.

image


با این تغییرات، هر دو کلاس تنها نیازمند واسط Graph هستند و پیاده‌سازی خاص کتابخانه را مستقیماً نمی‌بینند.

---

## ۴. استفاده از آداپتر در کلاس Main

در نهایت، در کلاس Main از GraphAdapter استفاده می‌کنیم تا شیء Graph<Integer, String> را بسازیم و سپس متدهای مختلف آن را فراخوانی کنیم.

image


در اینجا دیگر کلاس Main هم کد خودش را بر اساس واسط Graph نوشته است، و داخلی‌ترین جزئیات کتابخانهٔ Jung فقط در GraphAdapter پنهان شده‌اند.

---

## ۵. چرا از روش Object Adapter استفاده کرده‌ایم و نه Class Adapter؟

- Object Adapter (Composition)  
  این روش (ترکیب شیئی) باعث می‌شود آداپتر ما درون خود یک شیء از کلاس اصلی (مثلاً `SparseMultigraph`) داشته باشد و عملیات را به آن بسپارد. در جاوا به‌علت نبود ارث‌بری چندگانه، استفاده از Object Adapter معمولاً مناسب‌تر است. همچنین Coupling (وابستگی) کمتری میان آداپتر و کلاس اصلی ایجاد می‌شود و تغییر یا جایگزینی کتابخانه‌های دیگر به‌سادگی انجام خواهد شد.

- Class Adapter (Inheritance)  
  در این روش آداپتر مستقیماً از کلاس کتابخانه (مثلاً `SparseMultigraph`) ارث‌بری می‌کند که مشکلات زیر را در پی دارد:
  - اگر کلاس کتابخانه final باشد یا محدودیت دیگری داشته باشد، ارث‌بری ناممکن می‌شود.
  - در جاوا فقط یک کلاس می‌تواند والد باشد، بنابراین انعطاف‌پذیری را کاهش می‌دهد.
  - آداپتر به‌شدت به پیاده‌سازی داخلی آن کلاس کتابخانه وابسته می‌شود و تغییر آن را سخت می‌کند.

به همین دلایل، در اکثر موارد استفاده از روش Object Adapter به‌خصوص در زبان جاوا ترجیح داده می‌شود.