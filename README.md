
## 🔹 **3. Aplikasi: ProfileUpdateApp – Edit dan Preview Profile**

### 🎯 **Tujuan Pembelajaran**

- Memahami lifecycle saat berpindah ke Activity untuk edit, lalu kembali
- Membedakan behavior jika Activity di-_finish_ vs tetap di backstack
- Menerapkan `startActivityForResult()` / `registerForActivityResult` (modern)

### 📱 **Fitur Utama**

- `ProfileActivity`: Menampilkan nama, umur, email
- Tombol Edit → pindah ke `EditProfileActivity`
- Di `EditProfileActivity`, user bisa ubah data → klik Save → kembali ke `ProfileActivity` dengan data baru

### ✅ **Requirement Teknis**

- Gunakan 2 Activity: `ProfileActivity`, `EditProfileActivity`
- Gunakan `startActivityForResult()` atau modern `ActivityResultLauncher`
- Tampilkan log lifecycle untuk semua perpindahan
- Update tampilan setelah menerima hasil dari Activity lain

### 🔍 **Eksplorasi Lifecycle**

- Apakah `ProfileActivity` tetap aktif saat berpindah ke Edit?
- Lifecycle apa yang terpanggil saat kembali dari Edit?
- Bagaimana data dikirim kembali setelah Activity selesai?

---
