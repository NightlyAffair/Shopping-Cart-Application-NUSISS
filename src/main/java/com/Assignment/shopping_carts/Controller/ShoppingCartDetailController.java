package com.Assignment.shopping_carts.Controller;


import com.Assignment.shopping_carts.InterfaceMethods.ShoppingCartDetailInterface;
import com.Assignment.shopping_carts.Model.ShoppingCartDetail;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ShoppingCartDetail Controller
 * Author: Tony Song
 * Date: 2025-10-02
 * participants : Tony Song /  jason zhou
 * Last Modified by : jason zhou
 * Last Modified: 2025-10-13 21:00
 */


@RequestMapping("/products/cart")
@Controller
public class ShoppingCartDetailController {
    @Autowired
    private ShoppingCartDetailInterface cartService;//自动注入service interface中的method（mothodName应该是对应的）

    @PostMapping("/add")//处理“/shoppingCartDetail/add”的POST请求
    public String addToCart(@RequestParam int productId, @RequestParam int quantity, Model model,@RequestParam(required=false) String redirectUrl, HttpSession session) {
        int customerId = (int)session.getAttribute("customerId");
        cartService.addProductToCart(customerId, productId, quantity);//调用interface中addProductToCart方法
        model.addAttribute("productId", productId);//给页面传数据（当前操作的product的id）
        model.addAttribute("message", "Add to Cart Successfully!");//放提示语
        if (redirectUrl != null && !redirectUrl.isBlank()) {
            return "redirect:" + redirectUrl;
        }
        return "redirect:/products/details/" + productId;
    }

    @GetMapping("/view")//处理“/shoppingCartDetail/view”的GET请求
    public String showCart(Model model, HttpSession session) {
        int customerId = (int)session.getAttribute("customerId");
        //因为这里view到的大部分不止一个product，所以用list
        List<ShoppingCartDetail> products = cartService.showCart(customerId);//调用interface中addProductToCart方法
        double totalPrice = cartService.sumTotal(customerId);//调用interface中sumTotal方法

        //如果session里已经有selectedProducts，则复用；否则才new一个（保证勾选后更新quantity会丢失勾选记录）
        Set<Integer> selected = (Set<Integer>) session.getAttribute("selectedProducts");
        if (selected == null) {
            selected = new HashSet<>();
            session.setAttribute("selectedProducts", selected);
        }

        model.addAttribute("products", products);//给页面传products
        model.addAttribute("totalPrice", totalPrice);//给页面传totalPrice
        model.addAttribute("customerId", customerId);//给页面传customerId
        model.addAttribute("selectedProducts", selected);//给页面传selected
        return "shoppingCart";
    /*    1a.用户刚进cart界面，是没有任何select product的，这时候new HashSet<>()
        1b.下次再来如果上次有勾选没买的，再勾选其他的就直接加进去了（页面保存上次勾选的记录）
        2.选择结束后，controller传给前端，用户就看到对应product被勾选了*/
    }

    @PostMapping("/select")//处理“/shoppingCartDetail/select”的Post请求
    @ResponseBody
    public String selectProduct(@RequestParam int productId, @RequestParam boolean checked, HttpSession session) {
        //取出名为"selectedProducts"的HashSet<Integer>中的productId（刚进来肯定是空的）
        //Note：用户必须进入cart才能checkout，没有直接购买的情况
        Set<Integer> selected = (Set<Integer>) session.getAttribute("selectedProducts");

        //下面判断是否用户勾选了
        if (checked) {//check为true，意味着被勾选了
            selected.add(productId);//对应productId加进"selectedProducts"中
        }
        else {//check为false，意味着没被勾选
            selected.remove(productId);//对应productId删掉
        }
        session.setAttribute("selectedProducts", selected);//session给界面传被选中的productId
        return "OK";
    }

    @PostMapping("/plus")
    public String increment(@RequestParam int productId, Model model, HttpSession session) {
        int customerId = (int)session.getAttribute("customerId");
        cartService.addOne(customerId, productId);//调用interface中addOne方法
        return showCart(model, session);//更新后重新显示购物车页面
    }

    @PostMapping("/minus")
    public String decrement(@RequestParam int productId, Model model, HttpSession session) {
        int customerId = (int)session.getAttribute("customerId");
        cartService.deleteOne(customerId, productId);//调用interface中deleteOne方法
        return showCart(model, session);//更新后重新显示购物车页面
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam int productId, Model model, HttpSession session) {
        int customerId = (int)session.getAttribute("customerId");
        cartService.removeProduct(customerId, productId);//调用interface中removeProduct方法
        return showCart(model, session);//更新后重新显示购物车页面
    }

    @PostMapping("/clear")
    public String clearCart(Model model, HttpSession session) {
        int customerId = (int)session.getAttribute("customerId");
        cartService.clearCart(customerId);//调用interface中clearCart方法
        return showCart(model, session);//更新后重新显示购物车页面
    }

    @PostMapping("/payment")
    public String payment(Model model, HttpSession session) {
        int customerId = (int)session.getAttribute("customerId");
        model.addAttribute("customerId", customerId);
        return "creditCardDetails";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam int customerId, HttpSession session) {
        //取出名为"selectedProducts"的HashSet<Integer>中的productId
        Set<Integer> selected = (Set<Integer>) session.getAttribute("selectedProducts");
        if (selected != null && !selected.isEmpty()) {//selected如果存在且不为空
            //只要点进cart，selected就创建了，但可能Empty
            //如果这里用户进cart先勾选了又取消了，就是(selected != null && selected.isEmpty())，因此要避免
            cartService.recordOrder(customerId,selected);
            for (Integer productId : selected) {//遍历所有被勾选的product
                cartService.removeProduct(customerId, productId);//调用interface中removeProduct方法
            }//因为checkout过了，cart就删掉对应的product了
            session.removeAttribute("selectedProducts");//删除session里的selectedProducts
        }
        return "redirect:/products/cart/checkoutSuccess?customerId=" + customerId;//跳转结算成功页面
    }

    @GetMapping("/checkoutSuccess")
    public String checkoutSuccess(@RequestParam int customerId, Model model) {
        model.addAttribute("customerId", customerId);//给页面传customerId
        return "checkout"; // 渲染 checkout.html
    }


    //resume add to cart action
    @GetMapping("/resume")
    public String resumeCart(HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        String productIdStr = (String) session.getAttribute("pendingProductId");
        String quantityStr = (String) session.getAttribute("pendingQuantity");

        if (customerId != null && productIdStr != null) {
            int productId = Integer.parseInt(productIdStr);
            int quantity = quantityStr != null ? Integer.parseInt(quantityStr) : 1;

            cartService.addProductToCart(customerId, productId, quantity);
            session.removeAttribute("pendingProductId");
            session.removeAttribute("pendingQuantity");
            session.removeAttribute("redirectAfterLogin");
            session.removeAttribute("pendingProductId");
            return "redirect:/products/details/" + productId;
        }
        return "redirect:/products/page";
    }

}
