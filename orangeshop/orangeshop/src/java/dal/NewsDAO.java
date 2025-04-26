/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import model.News;
import model.NewsCategory;
import utils.DBContext;

/**
 *
 * @author hello
 */
public class NewsDAO extends DBContext {

    public List<News> getNewsByCategoryId(int category, int page, int newsPerPage) {
        List<News> list = new ArrayList();
        String sql = "SELECT *"
                + "FROM [dbo].[News]\n"
                + "WHERE [groupId] = ?\n"
                + "ORDER BY [ID] DESC\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, category);
            st.setInt(2, (page - 1) * newsPerPage);
            st.setInt(3, newsPerPage);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setId(rs.getInt("ID"));
                n.setName(rs.getString("Newsname"));
                n.setCategory(rs.getInt("groupId"));
                n.setContent(rs.getString("content"));
                n.setImage(rs.getString("image"));
                n.setUserid(rs.getInt("Userid"));
                list.add(n);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<News> get5NewPost() {
        List<News> list = new ArrayList();
        String sql = "SELECT TOP 5 [ID],\n"
                + "       [image],\n"
                + "       [Newsname]\n"
                + "FROM [dbo].[News]\n"
                + "WHERE [groupId] IN (1, 2, 3, 4, 5)\n"
                + "ORDER BY [ID] DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new News(rs.getInt(1), rs.getString(3), (null), rs.getString(2), 0, 0));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<NewsCategory> getNewsCategories() {
        List<NewsCategory> list = new ArrayList();
        String sql = "SELECT [NGroupId]\n"
                + "      ,[name]\n"
                + "  FROM [dbo].[NewsCategory]\n"
                + "WHERE [NgroupId] IN (1, 2, 3, 4, 5)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new NewsCategory(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewsCategory> getNewsCategoriesManagement() {
        List<NewsCategory> list = new ArrayList();
        String sql = "SELECT [NGroupId]\n"
                + "      ,[name]\n"
                + "  FROM [dbo].[NewsCategory]\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new NewsCategory(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public News getNewsById(int id) {
        News n = new News();
        String sql = "SELECT \n"
                + "    [ID],\n"
                + "	[Newsname],\n"
                + "    [content],\n"
                + "    [image],\n"
                + "    [groupId]\n"
                + "FROM [dbo].[News]\n"
                + "WHERE [ID] = " + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                n = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), 0);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    public int getNumOfPage(int category, int newsPerPage) {
        int numOfRow = 0;
        String sql = "SELECT COUNT(*) AS TOTALROWS FROM [dbo].[News]\n"
                + "WHERE [groupId] = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, category);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                numOfRow = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        int numOfPage = (numOfRow + newsPerPage - 1) / newsPerPage;
        return numOfPage;
    }

    public List<News> searchNewsByName(String newsName) {
        String[] newsWords = newsName.split("\\s+");
        List<News> listNews = new ArrayList<>();
        Map<News, Integer> map = new LinkedHashMap<>();
        String sql = "SELECT *, COUNT(*) AS totalOccurance\n"
                + "FROM\n"
                + "    (";
        for (int i = 0; i < newsWords.length; i++) {
            sql += "SELECT *\n"
                    + "    FROM [dbo].[News]\n"
                    + "    WHERE [Newsname] LIKE N'%" + newsWords[i] + "%' \n";
            if (i < newsWords.length - 1) {
                sql += "UNION ALL \n";
            }
        }
        sql += ") AS a \n"
                + "GROUP BY ID, Userid, content, image, groupId, Newsname\n"
                + "ORDER BY totalOccurance DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setId(rs.getInt("ID"));
                n.setName(rs.getString("Newsname"));
                n.setCategory(rs.getInt("groupId"));
                n.setContent(rs.getString("content"));
                n.setImage(rs.getString("image"));
                n.setUserid(rs.getInt("Userid"));
                int totalOccurance = rs.getInt("totalOccurance");
                map.put(n, totalOccurance);
                listNews.add(n);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
//        //xử lý theo thứ tự xuất hiện của từ
        List<Map.Entry<News, Integer>> list = new ArrayList<>(map.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<News, Integer>>() {
//            @Override
//            public int compare(Map.Entry<News, Integer> entry1, Map.Entry<News, Integer> entry2) {
//                if (Objects.equals(entry1.getValue(), entry2.getValue())) {
//                    for (String s : newsWords) {
//                        boolean containsS1 = entry1.getKey().getName().contains(s);
//                        boolean containsS2 = entry2.getKey().getName().contains(s);
//                        if (containsS1 && !containsS2) {
//                            return -1;
//                        } else if (!containsS1 && containsS2) {
//                            return 1;
//                        }
//                    }
//                }
//                return 0;
//            }
//        });
//        List<News> bestFit = new ArrayList<>();
//        for (Map.Entry<News, Integer> entry : list) {
//            bestFit.add(entry.getKey());
//        }
        return listNews;
    }

    public Map<News, String> searchNewsManagement(String newsName) {
        String[] newsWords = newsName.split("\\s+");
        Map<News, String> map = new LinkedHashMap<>();
        String sql = "SELECT *, COUNT(*) AS totalOccurance\n"
                + "FROM\n"
                + "(SELECT ID, Newsname, [user].fullname from (";
        for (int i = 0; i < newsWords.length; i++) {
            sql += "SELECT ID, Newsname, Userid\n"
                    + "    FROM [dbo].[News]\n"
                    + "    WHERE [Newsname] LIKE N'%" + newsWords[i] + "%' \n";
            if (i < newsWords.length - 1) {
                sql += "UNION ALL \n";
            }
        }
        sql += ") as a\n"
                + "JOIN [user] on [user].Userid = a.Userid) as b\n"
                + "GROUP BY ID, Newsname, fullname\n"
                + "ORDER BY totalOccurance DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setId(rs.getInt("ID"));
                n.setName(rs.getString("Newsname"));
                String authorName = rs.getString("fullname");
                map.put(n, authorName);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return map;
    }

    public List<News> relatedNews(int newsId, String newsName) {
        String[] newsWords = newsName.split("\\s+");
        List<News> list = new ArrayList();
        String sql = "SELECT TOP 3\n"
                + "	[ID],\n"
                + "	LEFT([content], 250) AS [SubContent],\n"
                + "	[image],\n"
                + "	[groupId],\n"
                + "	[Newsname]\n"
                + "FROM News\n"
                + "WHERE [ID] != ?\n"
                + "	AND ([Newsname] LIKE ?";
        for (int i = 1; i <= newsWords.length; i++) {
            sql += " OR [Newsname] LIKE ? ";
        }
        sql += ")";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int i = 1;
            st.setInt(i++, newsId);
            st.setString(i++, "%" + newsName + "%");
            for (String s : newsWords) {
                st.setString(i++, "%" + s + "%");
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new News(rs.getInt(1), rs.getString(5), rs.getString(2), rs.getString(3), rs.getInt(4), 0));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getIdMovingTo(int category, int newsId, String move) {
        int id = 0;
        String sql = "SELECT TOP 1 * FROM \n"
                + "(\n"
                + "SELECT TOP 1 [ID] \n"
                + "FROM [dbo].[News] \n"
                + "WHERE [groupId] = ? AND [ID] " + (move.equals("previous") ? ">" : "<") + " ? \n"
                + "ORDER BY [ID] " + (move.equals("previous") ? "ASC" : "DESC") + "\n"
                + "UNION ALL \n"
                + "SELECT [ID] \n"
                + "FROM [dbo].[News] \n"
                + "WHERE [groupId] = ? AND [ID] = (SELECT " + (move.equals("previous") ? "MIN" : "MAX") + "([ID]) FROM News) \n"
                + ") AS A";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, category);
            st.setInt(2, newsId);
            st.setInt(3, category);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    public Map<News, String> getNewsManagement(int category, int page, int recordPerPage) {
        Map<News, String> map = new LinkedHashMap<>();
        String sql = "SELECT [ID]\n"
                + "      ,[Newsname]\n"
                + "	  ,[fullname]\n"
                + "FROM [dbo].[News] a\n"
                + "JOIN [dbo].[user] b ON b.[userid] = a.Userid\n"
                + "WHERE [groupId] = ?\n"
                + "ORDER BY [ID] DESC\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, category);
            ps.setInt(2, (page - 1) * recordPerPage);
            ps.setInt(3, recordPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(new News(rs.getInt(1), rs.getString(2), null, null, 0, 0), rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return map;
    }

    public int getMinCategoryId() {
        int id = 0;
        String sql = "SELECT MIN([NGroupId]) FROM NewsCategory";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    public void addNews(int Userid, int newsCategory, String newsName, String newsContent, String imageName) {
        String sql = "INSERT INTO [dbo].[News]\n"
                + "           ([Userid]\n"
                + "           ,[groupId]\n"
                + "           ,[Newsname]\n"
                + "           ,[content]\n"
                + "           ,[image])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Userid);
            st.setInt(2, newsCategory);
            st.setString(3, newsName);
            st.setString(4, newsContent);
            st.setString(5, imageName);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateNews(int newsId, int Userid, int newsCategory, String newsName, String newsContent, String imageName) {
        String sql = "UPDATE [dbo].[News]\n"
                + "   SET [Userid] = ?\n"
                + "      ,[groupId] = ?\n"
                + "      ,[Newsname] = ?\n"
                + "      ,[content] = ?\n"
                + "      ,[image] = ?\n"
                + " WHERE [ID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Userid);
            st.setInt(2, newsCategory);
            st.setString(3, newsName);
            st.setString(4, newsContent);
            st.setString(5, imageName);
            st.setInt(6, newsId);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteNewsById(int newsId) {
        String sql = "DELETE FROM [dbo].[News] WHERE [ID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, newsId);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addNewsCategory(String addNewsCategory) {
        String sql = "INSERT INTO [dbo].[NewsCategory] ([name]) VALUES (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, addNewsCategory);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateNewsCategory(int id, String newsCategoryName) {
        String sql = "UPDATE [dbo].[NewsCategory]\n"
                + "   SET [name] = ?\n"
                + " WHERE [NGroupId] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newsCategoryName);
            st.setInt(2, id);
            st.executeQuery();
        } catch (SQLException e) {

        }
    }

    public void deleteNewsCategoryById(int categoryId) {
        String sql = "DELETE FROM [dbo].[NewsCategory] WHERE [NGroupId] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public NewsCategory getNewsCategoryById(int id) {
        String sql = "SELECT [NGroupId], [name]\n"
                + "FROM [dbo].[NewsCategory]\n"
                + "WHERE [NGroupId] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new NewsCategory(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public static void main(String[] args) {
        NewsDAO dao = new NewsDAO();
        System.out.println(dao.searchNewsByName("a").size());
    }
    public List<NewsCategory> searchNewsCategoryByName(String newsCategoryName) {
        String[] words = newsCategoryName.split("\\s+");
        List<NewsCategory> list = new ArrayList<>();
        String sql = "SELECT [NGroupId], [name]\n"
                + "FROM [dbo].[NewsCategory]\n"
                + "WHERE ";
        for(int i = 0; i < words.length; i++){
            sql += " [name] LIKE N'%" + words[i] + "%'\n";
            if(i < words.length - 1)
                sql += " OR \n";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new NewsCategory(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public int maxNewsPageManagement(int category, int recordPerPageManagement) {
        int numOfRow = 0;
        String sql = "SELECT COUNT(*) AS TOTALROWS FROM [dbo].[News]\n"
                + "WHERE [groupId] = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, category);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                numOfRow = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        int numOfPage = (numOfRow + recordPerPageManagement - 1) / recordPerPageManagement;
        return numOfPage;
    }
    
}
