package com.wander4096.bangumix

import com.wander4096.bangumix.data.Anime
import com.wander4096.bangumix.data.AnimeComment
import com.wander4096.bangumix.data.AnimeFullInfo
import com.wander4096.bangumix.data.User
import com.wander4096.bangumix.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
class Cowboy @Autowired constructor(
        private val userService: UserService,
        private val animeService: AnimeService,
        private val commentService: CommentService,
        private val rankService: RankService,
        private val recommendService: RecommendService
){
    @GetMapping("/")
    fun index(session: HttpSession, model: Model): String {
        model.addAttribute("animes", animeService.findAllFullInformation())
        val username = session.getAttribute("user")
        model.addAttribute("user", username)
        username?.let {
            val recommend = recommendService.recommendForUser(it as String)
            model.addAttribute("recommendAnimes", recommend)
        }
        return "index"
    }

    @GetMapping("/anime")
    fun anime(@RequestParam("animeName") animeName: String,
              session: HttpSession,
              model: Model): String {
        animeService.findByName(animeName)?.let { anime ->
            model.addAttribute("anime", anime)
            model.addAttribute("comments", commentService.findAllByAnime(animeName))
            val user = session.getAttribute("user")
            model.addAttribute("user", user)
            user?.let { username ->
                val point = rankService.findByAnimeAndUser(animeName, username as String)
                if (null != point) {
                    model.addAttribute("point", point.point)
                } else {
                    model.addAttribute("point", "尚未评分")
                }

            }
            return "anime"
        } ?: return "error/404"
    }

    @GetMapping("login")
    fun login(): String {
        return "login"
    }

    @PostMapping("login")
    fun login(@RequestParam username: String,
              @RequestParam password: String,
              redirect: RedirectAttributes,
              session: HttpSession): String {
        if (userService.checkUsername(username) != null) {
            if (userService.checkUsernameAndPassword(username, password) != null) {
                session.setAttribute("user", username)
//                redirect.addFlashAttribute("loginSuccess", true)
                return "redirect:/"
            } else {
                redirect.addFlashAttribute("errorMessage", "密码错误！")
                return "redirect:/login"
            }
        } else {
            redirect.addFlashAttribute("errorMessage", "用户不存在！")
            return "redirect:/login"
        }
    }

    @GetMapping("/logout")
    fun logout(session: HttpSession): String {
        session.removeAttribute("user")
        return "redirect:/"
    }

    @GetMapping("/register")
    fun register(): String {
        return "register"
    }

    @PostMapping("/register")
    fun register(@RequestParam username: String,
                 @RequestParam password: String,
                 @RequestParam repeatPassword: String,
                 redirect: RedirectAttributes): String {
        if (password != repeatPassword) {
            redirect.addFlashAttribute("errorMessage", "两次输入的密码不一致！")
            return "redirect:/register"
        } else {
            try {
                userService.registerUser(User(username, password))
            } catch (e: IllegalArgumentException) {
                redirect.addFlashAttribute("errorMessage", e.message)
                return "redirect:/register"
            }
        }
        return "redirect:/"
    }

    @PostMapping("/add/anime")
    fun addAnime(@RequestParam animeName: String,
                 @RequestParam directorName: String,
                 @RequestParam synopsis: String,
                 redirect: RedirectAttributes): String {
        try {
            animeService.insertOne(Anime(animeName, directorName, synopsis))
        } catch (e: IllegalArgumentException) {
            redirect.addFlashAttribute("errorMessage", e.message)
        }
        return "redirect:/"
    }

    @PostMapping("/add/comment")
    fun addComment(@RequestParam animeName: String,
                   @RequestParam commentContent: String,
                   session: HttpSession,
                   redirect: RedirectAttributes): String {
        redirect.addAttribute("animeName", animeName)
        try {
            commentService.insertOne(AnimeComment(0, animeName, session.getAttribute("user") as String, commentContent))
        } catch (e: IllegalArgumentException) {
            redirect.addFlashAttribute("errorMessage", e.message)
        } catch (e: Exception) {
            redirect.addFlashAttribute("errorMessage", "未知错误")
        }
        return "redirect:/anime"
    }

    @PostMapping("/add/point")
    fun addPoint(@RequestParam animeName: String,
                 @RequestParam point: String,
                 session: HttpSession,
                 redirect: RedirectAttributes): String {
        redirect.addAttribute("animeName", animeName)
        try {
            rankService.insertOne(animeName, session.getAttribute("user") as String, point)
        } catch (e: IllegalArgumentException) {
            redirect.addFlashAttribute("errorMessage", e.message)
        } catch (e: Exception) {
            redirect.addFlashAttribute("errorMessage", "未知错误")
        }
        return "redirect:/anime"
    }
}