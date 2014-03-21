/*
 * jQuery liTabs v 2.1 (14.08.13)
 *
 * Copyright 2012, Linnik Yura | LI MASS CODE | http://masscode.ru
 * http://masscode.ru/index.php/k2/item/43-litabs
 * Free to use
 *
 */
(function($) {
    $.fn.liTabs = function(params) {
        var p = $.extend({
            duration: 500, // A string or number determining how long the animation will run
            effect: 'clasic', //simple, fade, hSlide, vSlide
            block: false,
            equal: false,
            tLast: false,
            onClick: function(){}
        }, params);
        return this.each(function() {

            var
            tWrap = $(this),
                tItem = tWrap.children('.t_item'),
                tContents = tWrap.children('.t_item').children('.t_content'),
                tLinks = tWrap.children('.t_item').children('.t_link');

            if (p.tLast == true) {
                tItem.last().addClass('t_last');
            }

            $('<div>').addClass('t_include').appendTo(tWrap);

            var
            tInclude = tWrap.children('.t_include'),
                tCur = tWrap.children('.t_item').children('a:first');
            if (tWrap.children('.t_item').children('.cur').length) {
                tCur = tWrap.children('.t_item').children('.cur');
            }
            tCur.addClass('cur');

            if (p.block == true) {
                var ttBlock = $('<div>').addClass('t_item_block');
                ttBlock.prependTo(tWrap);
                tItem.appendTo(ttBlock);
            }

            if (p.equal == true) {
                var maxHContent = function() {
                    var maxH = 0;
                    $('.t_content', tWrap).each(function() {
                        var mH = $(this).height();
                        if (mH > maxH) {
                            maxH = mH;
                        }
                    });
                    $('.t_include', tWrap).height(maxH);
                };

                function onResize() {
                    maxHContent();
                };

                tWrap.bind("refreshSize", function() {
                    maxHContent();
                });

                var timer;

                $(window).resize(function() {
                    timer && clearTimeout(timer);
                    timer = setTimeout(onResize, 200);
                })

                $(window).load(function() {
                    setTimeout(function() {
                        maxHContent();
                    }, 100);
                });
            }


            tCur.parent().children('.t_content').show();
            if (tWrap.hasClass('calcItm')) {
                $(window).ready(function() {
                    var ml = 0;
                    tItem.each(function() {
                        var mL = parseInt($(this).css('margin-left'), 10);
                        ml = ml + mL;
                    });
                    var wW = parseInt(tWrap.width(), 10) - ml;
                    var aw = 0;
                    var lCol = tItem.length;
                    tItem.children('a').each(function() {
                        var aW = $(this).width();
                        aw = aw + aW;
                    });
                    var pad = wW - aw;
                    var pD = (pad / lCol) / 2;
                    tLinks.each(function() {
                        $(this).css({
                            'padding-left': '' + pD - 1 + 'px',
                            'padding-right': '' + pD - 1 + 'px'
                        });
                    });
                });
            };

            tItem.each(function() {

                var
                tItemEl = $(this),
                    tCont = tItemEl.children('.t_content').appendTo(tInclude),
                    tLink = tItemEl.children('.t_link');

                tLink.on('click', function() {
                    if($(this).hasClass("disable")){
                        return false;
                    }
                    if (!$(this).is('.cur')) {
                        tLinks.removeClass('cur').filter(this).addClass('cur');
                        if (p.effect == 'clasic') {
                            tContents.hide().filter(tCont).show();
                        };
                        if (p.effect == 'fade') {
                            tContents.fadeOut(p.duration).filter(tCont).fadeIn(p.duration);
                        };
                        if (p.effect == 'hSlide') {
                            tContents.stop().animate({
                                left: '-10%',
                                opacity: '0'
                            }, p.duration, function() {
                                $(this).hide();
                            }).filter(tCont).stop().css({
                                left: '10%'
                            }).show().animate({
                                    left: '0',
                                    opacity: '1'
                                }, p.duration,
                                function() {

                                });
                        };
                        if (p.effect == 'vSlide') {
                            tContents.stop().animate({
                                top: '30px',
                                opacity: '0'
                            }, p.duration, function() {
                                $(this).hide();
                            }).filter(tCont).stop().css({
                                top: '-30px'
                            }).show().animate({
                                top: '0',
                                opacity: '1'
                            }, p.duration);
                        };
                        $(this).trigger('liTabs-tab-changed');
                    };
                    p.onClick();
                    return false;
                });
            });
        });
    };
})(jQuery);

