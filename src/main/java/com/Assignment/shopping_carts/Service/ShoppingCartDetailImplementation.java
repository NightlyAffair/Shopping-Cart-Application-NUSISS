package com.Assignment.shopping_carts.Service;

import java.util.List;
import java.util.Optional;

import com.Assignment.shopping_carts.InterfaceMethods.ShoppingCartDetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Assignment.shopping_carts.Model.ShoppingCartDetail;
import com.Assignment.shopping_carts.Repository.ShoppingCartDetailRepository;

import jakarta.transaction.Transactional;

/**
 * ShoppingCartDetail Service Implementation
 * Author: Tony Song
 * Date: 2025-10-02
 * Modifier by : Tony Song
 * Last Modified by : Tony Song
 * Last Modified: 2025-10-07 23:00
 */

@Service
@Transactional
public class   ShoppingCartDetailImplementation implements ShoppingCartDetailInterface {
    @Autowired
    private ShoppingCartDetailRepository cartRepo;//注入购物车的repository，不需要手动new

    @Override
    //从产品界面添加product进购物车
    public void addProductToCart(int customerId, int productId, int quantity) {
        //调用Repository，先查这个customer的cart是否已经有这个product（optional意思是不一定存在）
        Optional<ShoppingCartDetail> opt = cartRepo.findByCustomerIdAndProductId(customerId, productId);

        if (opt.isPresent()) {//如果有值，则在原有product上更新数量
            ShoppingCartDetail detail = opt.get();//这条cartDetail就是找到数据对应的两个id和quantity
            detail.setQuantity(detail.getQuantity() + quantity);//原有数量+新增数量
            cartRepo.save(detail);//存入数据库
        }
        else {//如果没有，就要新建一个cartDetail然后存数据
            ShoppingCartDetail detail = new ShoppingCartDetail();//新建一个detail
            detail.setCustomerId(customerId);//设置customerId
            detail.setProductId(productId);//设置productId
            detail.setQuantity(quantity);//设置quantity
            cartRepo.save(detail);//存入数据库
        }
    }
    @Override
    //展示所有本用户的购物车信息
    public List<ShoppingCartDetail> showCart(int customerId) {
        return cartRepo.findByCustomerId(customerId);//根据顾客ID查出这个顾客购物车里的所有条目
    }

    @Override
    //修改购物车中某product的数量（用于textbox直接改，不是+或-）
    public void updateQuantity(int productId, int customerId, int newQuantity) {
        //调用Repository，通过customerId和productId找到对应的product
        ShoppingCartDetail product = cartRepo.findByCustomerIdAndProductId(customerId, productId).get();
        product.setQuantity(newQuantity);//设置新quantity
        cartRepo.save(product);//存入（更新）数据库
    }

    @Override
    //某个cart中的product数量+1
    public void addOne(int customerId, int productId) {
        //调用Repository，通过customerId和productId找到对应的product
        ShoppingCartDetail product = cartRepo.findByCustomerIdAndProductId(customerId, productId).get();
        product.setQuantity(product.getQuantity() + 1);//quantity在原有基础上+1
        cartRepo.save(product);//存入（更新）数据库
    }

    @Override
    //某个cart中的product数量-1
    public void deleteOne(int customerId, int productId) {
        //调用Repository，通过customerId和productId找到对应的product
        ShoppingCartDetail product = cartRepo.findByCustomerIdAndProductId(customerId, productId).get();
        int newQuantity = product.getQuantity() - 1;//定义一个新数字，数字为当前product的数量-1，用于后续判断
        if (newQuantity > 0) {//如果这个数仍然>0，意味着-1后购物车仍然有相同产品存在
            product.setQuantity(newQuantity);//更新新个数
            cartRepo.save(product);//存入（更新）数据库
        }
        else {//如果-1后等于0了，说明已经没有这个product在cart里了
            cartRepo.delete(product);//整个product从cart中删除
        }
    }

    @Override
    //无论product个数多少，将指定的product直接从cart中删除（界面上有勾选框，这里单个或多个都可以用）
    public void removeProduct(int customerId, int productId) {
        //调用Repository，删除对应customerId选中的product（productId）
        cartRepo.deleteByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    //清空购物车所有product，无视个数
    public void clearCart(int customerId) {
        //调用Repository，删除对应product的所有product（因为是删全部，所以不需要productId判断了）
        cartRepo.deleteByCustomerId(customerId);
    }

    @Override
    //计算购物车中product的总价（功能中是勾选框选中的产品自动计算总价）
    public double sumTotal(int customerId) {
        //调用Repository，查出这个顾客购物车里的所有product
        List<ShoppingCartDetail> products = cartRepo.findByCustomerId(customerId);
        double totalPrice = 0.0;//先定义总价为零，后续往里加
        if (products.isEmpty()) {//如果cart中压根product（为空），总价必是0
            totalPrice = 0.0;//总价为0
        }
        else{//如果不为0，说明cart中有product
            for (ShoppingCartDetail product : products) {//从products集合中遍历每个product
                totalPrice += product.getSubPrice();//遍历一个，加进总价一个，最后遍历的就是总价
            }
        }
        return totalPrice;//if-else结束，输出总价
    }
}
