import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection conn;

    public DAO() {
        conn = Koneksi.getConnection();
    }

    // CRUD operations for Admin

    public void createAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO administrator (nama_admin, pw_admin, privilege) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, admin.getNama());
        stmt.setString(2, admin.getPw());
        stmt.setString(3, admin.getPrivilege());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            admin.setId(rs.getInt(1));
        }
    }

    public Admin readAdmin(int id) throws SQLException {
        String sql = "SELECT * FROM administrator WHERE id_admin = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Admin(
                    rs.getInt("id_admin"),
                    rs.getString("nama_admin"),
                    rs.getString("pw_admin"),
                    rs.getString("privilege")
            );
        }
        return null;
    }

    public List<Admin> readAllAdmins() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM administrator";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            admins.add(new Admin(
                    rs.getInt("id_admin"),
                    rs.getString("nama_admin"),
                    rs.getString("pw_admin"),
                    rs.getString("privilege")
            ));
        }
        return admins;
    }

    public void updateAdmin(Admin admin) throws SQLException {
        String sql = "UPDATE administrator SET nama_admin = ?, pw_admin = ?, privilege = ? WHERE id_admin = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, admin.getNama());
        stmt.setString(2, admin.getPw());
        stmt.setString(3, admin.getPrivilege());
        stmt.setInt(4, admin.getId());
        stmt.executeUpdate();
    }

    public void deleteAdmin(int id) throws SQLException {
        String sql = "DELETE FROM administrator WHERE id_admin = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    // Authentication method
    public Admin authenticateAdmin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM administrator WHERE nama_admin = ? AND pw_admin = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Admin(
                    rs.getInt("id_admin"),
                    rs.getString("nama_admin"),
                    rs.getString("pw_admin"),
                    rs.getString("privilege")
            );
        }
        return null;
    }

    // CRUD operations for Film

    public void createFilm(Film film) throws SQLException {
        String sql = "INSERT INTO film (judul, sutradara, rating, harga) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, film.getJudul());
        stmt.setString(2, film.getSutradara());
        stmt.setFloat(3, film.getRating());
        stmt.setDouble(4, film.getHarga());
        stmt.executeUpdate();

        // Retrieve generated id_film
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            film.setId_film(rs.getInt(1));
        }
    }

    public Film readFilm(int id_film) throws SQLException {
        String sql = "SELECT * FROM film WHERE id_film = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id_film);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Film(
                    rs.getInt("id_film"),
                    rs.getString("judul"),
                    rs.getString("sutradara"),
                    rs.getFloat("rating"),
                    rs.getDouble("harga")
            );
        }
        return null;
    }

    public List<Film> readAllFilms() throws SQLException {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM film";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            films.add(new Film(
                    rs.getInt("id_film"),
                    rs.getString("judul"),
                    rs.getString("sutradara"),
                    rs.getFloat("rating"),
                    rs.getDouble("harga")
            ));
        }
        return films;
    }

    public void updateFilm(Film film) throws SQLException {
        String sql = "UPDATE film SET judul = ?, sutradara = ?, rating = ?, harga = ? WHERE id_film = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, film.getJudul());
        stmt.setString(2, film.getSutradara());
        stmt.setFloat(3, film.getRating());
        stmt.setDouble(4, film.getHarga());
        stmt.setInt(5, film.getId_film());
        stmt.executeUpdate();
    }

    public void deleteFilm(int id_film) throws SQLException {
        String sql = "DELETE FROM film WHERE id_film = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id_film);
        stmt.executeUpdate();
    }

    // CRUD operations for Transaksi

    public void createTransaksi(Transaksi transaksi) throws SQLException {
        String sql = "INSERT INTO transaksi (id_film, id_admin, no_kursi, jumlah_tiket, total_harga, tanggal_transaksi) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, transaksi.getId_film());
        stmt.setInt(2, transaksi.getId_admin());
        stmt.setString(3, transaksi.getNo_kursi());
        stmt.setInt(4, transaksi.getJumlah_tiket());
        stmt.setDouble(5, transaksi.getTotal_harga());
        stmt.setTimestamp(6, Timestamp.valueOf(transaksi.getTanggal_transaksi()));
        stmt.executeUpdate();
    }

    public Transaksi readTransaksi(int id_transaksi) throws SQLException {
        String sql = "SELECT * FROM transaksi WHERE id_transaksi = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id_transaksi);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Transaksi(
                    rs.getInt("id_transaksi"),
                    rs.getInt("id_film"),
                    rs.getInt("id_admin"),
                    rs.getString("no_kursi"),
                    rs.getInt("jumlah_tiket"),
                    rs.getDouble("total_harga"),
                    rs.getTimestamp("tanggal_transaksi").toLocalDateTime()
            );
        }
        return null;
    }

    public List<Transaksi> readAllTransaksi() throws SQLException {
        List<Transaksi> transaksis = new ArrayList<>();
        String sql = "SELECT * FROM transaksi";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            transaksis.add(new Transaksi(
                    rs.getInt("id_transaksi"),
                    rs.getInt("id_film"),
                    rs.getInt("id_admin"),
                    rs.getString("no_kursi"),
                    rs.getInt("jumlah_tiket"),
                    rs.getDouble("total_harga"),
                    rs.getTimestamp("tanggal_transaksi").toLocalDateTime()
            ));
        }
        return transaksis;
    }

    public void updateTransaksi(Transaksi transaksi) throws SQLException {
        String sql = "UPDATE transaksi SET id_film = ?, id_admin = ?, no_kursi = ?, jumlah_tiket = ?, total_harga = ?, tanggal_transaksi = ? WHERE id_transaksi = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, transaksi.getId_film());
        stmt.setInt(2, transaksi.getId_admin());
        stmt.setString(3, transaksi.getNo_kursi());
        stmt.setInt(4, transaksi.getJumlah_tiket());
        stmt.setDouble(5, transaksi.getTotal_harga());
        stmt.setTimestamp(6, Timestamp.valueOf(transaksi.getTanggal_transaksi()));
        stmt.setInt(7, transaksi.getId_transaksi());
        stmt.executeUpdate();
    }

    public void deleteTransaksi(int id_transaksi) throws SQLException {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id_transaksi);
        stmt.executeUpdate();
    }

    // Method to get taken seat numbers for a specific film
    public List<String> getTakenSeatsForFilm(int id_film) throws SQLException {
        List<String> takenSeats = new ArrayList<>();
        String sql = "SELECT no_kursi FROM transaksi WHERE id_film = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id_film);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            takenSeats.add(rs.getString("no_kursi"));
        }
        return takenSeats;
    }
}
