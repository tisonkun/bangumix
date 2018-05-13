package com.wander4096.bangumix

import com.wander4096.bangumix.data.Anime
import com.wander4096.bangumix.data.AnimeComment
import com.wander4096.bangumix.data.User
import com.wander4096.bangumix.service.AnimeService
import com.wander4096.bangumix.service.UserService
import com.wander4096.bangumix.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpSession

@Controller
class Cowboy @Autowired constructor(
        private val userService: UserService,
        private val animeService: AnimeService,
        private val commentService: CommentService
){
    @GetMapping("/")
    fun index(session: HttpSession, model: Model): String {
        model.addAttribute("animes", animeService.findAllWithRank())
        model.addAttribute("user", session.getAttribute("user"))
        return "index"
    }

    @GetMapping("/anime")
    fun anime(@RequestParam("animeName") animeName: String,
              session: HttpSession,
              model: Model): String {
        model.addAttribute("anime", animeService.findByName(animeName))
        model.addAttribute("comments", commentService.findAllByAnime(animeName))
        model.addAttribute("user", session.getAttribute("user"))
        return "anime"
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
        }
        return "redirect:/anime"
    }
}