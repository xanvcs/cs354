class CommentsController < ApplicationController
    before_action :set_post, only: [:create, :destroy]
    before_action :set_comment, only: [:destroy]
  
    def create
      @comment = @post.comments.new(comment_params)
      @comment.user = current_user
  
      if @comment.save
        redirect_to @post, notice: 'Your comment was successfully posted.'
      else
        redirect_to @post, alert: 'Your comment could not be posted.'
      end
    end
  
    def destroy
      @comment.destroy if @comment.user_id == current_user.id
      redirect_to @post, notice: 'Comment was successfully deleted.'
    end
  
    private
  
    def set_post
      @post = Post.find(params[:post_id])
    end
  
    def set_comment
      @comment = @post.comments.find(params[:id])
    end
  
    def comment_params
      params.require(:comment).permit(:content)
    end
  end
  